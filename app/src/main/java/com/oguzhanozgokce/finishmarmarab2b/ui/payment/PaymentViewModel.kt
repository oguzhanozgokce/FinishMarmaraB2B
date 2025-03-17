package com.oguzhanozgokce.finishmarmarab2b.ui.payment

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toExpirationDateFormat
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.CreditCartRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.OrderRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Location
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.GetBasketProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.PostCreditCartUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.PostOrderUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.DeleteLocationUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.GetCreditCartUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.GetUserLocationsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getBasketProductsUseCase: GetBasketProductsUseCase,
    private val getUserLocationsUseCase: GetUserLocationsUseCase,
    private val deleteLocationUseCase: DeleteLocationUseCase,
    private val postOrderUseCase: PostOrderUseCase,
    private val postCreditCartUseCase: PostCreditCartUseCase,
    private val getCreditCartUseCase: GetCreditCartUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        getBasketProducts()
        getUserLocations()
        getCreditCart()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.HideDialog -> hideDialog()
            is UiAction.ShowDialog -> showDialog()
            is UiAction.ShowAgreementDialog -> showAgreementDialog()
            is UiAction.HideAgreementDialog -> hideAgreementDialog()
            is UiAction.OnChangeAddress -> toggleLocationSelection(uiAction.location)
            is UiAction.OnChangeSelectedCard -> toggleCardSelection(uiAction.creditCart)
            is UiAction.OnCheckAgreement -> updateState { copy(isChecked = !isChecked) }
            is UiAction.OnCheckSaveCard -> updateState { copy(isSaveCardChecked = !isSaveCardChecked) }
            is UiAction.OnChangeCardNumber -> updateState { copy(cardNumber = uiAction.cardNumber) }
            is UiAction.OnChangeCardName -> updateState { copy(cardName = uiAction.cardName) }
            is UiAction.OnChangeCardTitle -> updateState { copy(cardTitle = uiAction.cardTitle) }
            is UiAction.OnChangeExpirationDate -> updateState {
                copy(
                    expirationDateValue = uiAction.expirationDateValue
                )
            }

            is UiAction.OnChangeCvv -> updateState { copy(cvv = uiAction.cvv) }
            is UiAction.DeleteLocation -> deleteLocation(uiAction.locationId)
            is UiAction.PostOrder -> postOrder()
        }
    }

    private fun hideDialog() = updateState { copy(showDialog = false) }
    private fun showDialog() = updateState { copy(showDialog = true) }
    private fun showAgreementDialog() = updateState { copy(isShowAgreementDialog = true) }
    private fun hideAgreementDialog() = updateState { copy(isShowAgreementDialog = false) }
    private fun toggleLocationSelection(location: Location) {
        updateState { copy(selectedLocation = if (selectedLocation == location) null else location) }
    }

    private fun toggleCardSelection(creditCart: CreditCart) {
        updateState { copy(selectedCard = if (selectedCard == creditCart) null else creditCart) }
    }

    private fun getBasketProducts() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getBasketProductsUseCase().fold(onSuccess = { basketProducts ->
                updateState {
                    copy(
                        products = basketProducts,
                        totalPrice = basketProducts.sumOf { it.totalPrice },
                        totalCartCount = basketProducts.sumOf { it.count },
                        isLoading = false
                    )
                }
            }, onError = { error ->
                updateState { copy(isLoading = false) }
                emitUiEffect(UiEffect.ShowToast(error))
            })
        }
    }

    private fun getUserLocations() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getUserLocationsUseCase().fold(onSuccess = { addressList ->
                updateState { copy(locationList = addressList, isLoading = false) }
            }, onError = { error ->
                updateState { copy(isLoading = false, errorMessage = error) }
            })
        }
    }

    private fun deleteLocation(locationId: Int) {
        viewModelScope.launch {
            deleteLocationUseCase(locationId).fold(
                onSuccess = {
                    emitUiEffect(UiEffect.ShowToast("Location Deleted"))
                },
                onError = { error ->
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }

    private fun postOrder() {
        if (!currentState.isChecked) {
            showAgreementDialog()
            return
        }
        if (currentState.isSaveCardChecked) {
            postCreditCart()
            return
        }
        processOrder()
    }

    private fun postCreditCart() {
        val request = CreditCartRequest(
            cardNumber = currentState.cardNumber.trim(),
            cardNameSurname = currentState.cardName,
            cardTitle = currentState.cardTitle,
            lastDate = currentState.expirationDateValue.toExpirationDateFormat(),
            cardCvv = currentState.cvv
        )
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            postCreditCartUseCase(request).fold(
                onSuccess = {
                    updateState { copy(isLoading = false) }
                    processOrder()
                },
                onError = { error ->
                    updateState { copy(isLoading = false) }
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }

    private fun processOrder() {
        val request = OrderRequest(
            locationId = currentState.selectedLocation?.id,
            creditCardId = currentState.selectedCard?.id,
            totalPrice = currentState.totalPrice,
        )
        viewModelScope.launch {
            postOrderUseCase(request)
                .onStart { updateState { copy(isLoading = true) } }
                .onCompletion { updateState { copy(isLoading = false) } }
                .onEach { resource ->
                    resource.fold(
                        onSuccess = {
                            emitUiEffect(UiEffect.NavigateToOrderSuccess)
                        },
                        onError = { error ->
                            emitUiEffect(UiEffect.ShowToast(error))
                        }
                    )
                }
                .launchIn(viewModelScope)
        }
    }

    private fun getCreditCart() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getCreditCartUseCase().fold(
                onSuccess = { creditCartList ->
                    updateState { copy(creditCartList = creditCartList, isLoading = false) }
                },
                onError = { error ->
                    updateState { copy(isLoading = false) }
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }
}
