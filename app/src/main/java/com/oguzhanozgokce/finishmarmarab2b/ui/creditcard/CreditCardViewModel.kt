package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toExpirationDateFormat
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.CreditCartRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.PostCreditCartUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.GetCreditCartUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreditCardViewModel @Inject constructor(
    private val getCreditCartUseCase: GetCreditCartUseCase,
    private val postCreditCartUseCase: PostCreditCartUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        getCreditCart()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.OnChangeCardName -> updateState { copy(cardName = uiAction.cardName) }
            is UiAction.OnChangeCardNumber -> updateState { copy(cardNumber = uiAction.cardNumber) }
            is UiAction.OnChangeExpirationDate -> updateState {
                copy(
                    expirationDate = uiAction.expirationDate
                )
            }

            is UiAction.OnChangeCvv -> updateState { copy(cvv = uiAction.cvv) }
            is UiAction.OnChangeCardTitle -> updateState { copy(cardTitle = uiAction.cardTitle) }
            is UiAction.HideBottomSheet -> hideBottomSheet()
            is UiAction.ShowBottomSheet -> showBottomSheet()
            is UiAction.AddCreditCard -> addCreditCard()
        }
    }

    private fun hideBottomSheet() = updateState { copy(isShowBottomSheet = false) }
    private fun showBottomSheet() = updateState { copy(isShowBottomSheet = true) }

    private fun getCreditCart() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getCreditCartUseCase().fold(
                onSuccess = { creditCartList ->
                    updateState { copy(isLoading = false, creditCardList = creditCartList) }
                },
                onError = { error ->
                    updateState { copy(isLoading = false, error = error) }
                }
            )
        }
    }

    private fun addCreditCard() {
        val request = CreditCartRequest(
            cardNumber = currentState.cardNumber.trim(),
            lastDate = currentState.expirationDate.toExpirationDateFormat(),
            cardCvv = currentState.cvv,
            cardTitle = currentState.cardTitle,
            cardNameSurname = currentState.cardName
        )
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            postCreditCartUseCase(request).fold(
                onSuccess = {
                    clearCreditCardFields()
                    hideBottomSheet()
                    getCreditCart()
                },
                onError = { error ->
                    updateState { copy(isLoading = false, error = error) }
                }
            )
        }
    }

    private fun clearCreditCardFields() {
        updateState {
            copy(
                isLoading = false,
                cardNumber = "",
                expirationDate = "",
                cvv = "",
                cardTitle = "",
                cardName = ""
            )
        }
    }
}
