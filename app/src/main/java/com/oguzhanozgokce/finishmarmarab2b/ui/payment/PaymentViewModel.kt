package com.oguzhanozgokce.finishmarmarab2b.ui.payment

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.GetBasketProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.DeleteLocationUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.GetUserLocationsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getBasketProductsUseCase: GetBasketProductsUseCase,
    private val getUserLocationsUseCase: GetUserLocationsUseCase,
    private val deleteLocationUseCase: DeleteLocationUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        getBasketProducts()
        getUserLocations()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.HideDialog -> hideDialog()
            is UiAction.ShowDialog -> showDialog()
            is UiAction.ShowAgreementDialog -> showAgreementDialog()
            is UiAction.HideAgreementDialog -> hideAgreementDialog()
            is UiAction.OnChangeAddress -> updateState { copy(selectedLocation = uiAction.location) }
            is UiAction.OnChangeSelectedCard -> updateState { copy(selectedCard = uiAction.creditCart) }
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
        }
    }

    private fun hideDialog() = updateState { copy(showDialog = false) }
    private fun showDialog() = updateState { copy(showDialog = true) }
    private fun showAgreementDialog() = updateState { copy(isShowAgreementDialog = true) }
    private fun hideAgreementDialog() = updateState { copy(isShowAgreementDialog = false) }

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
}
