package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart

object CreditCardContract {
    data class UiState(
        val isLoading: Boolean = false,
        val creditCardList: List<CreditCart> = emptyList(),
        val cardName: String = "",
        val cardNumber: String = "",
        val expirationDate: String = "",
        val cvv: String = "",
        val cardTitle: String = "",
        val isShowBottomSheet: Boolean = false,
        val error: String = "",
    )

    sealed class UiAction {
        data class OnChangeCardName(val cardName: String) : UiAction()
        data class OnChangeCardNumber(val cardNumber: String) : UiAction()
        data class OnChangeExpirationDate(val expirationDate: String) : UiAction()
        data class OnChangeCvv(val cvv: String) : UiAction()
        data class OnChangeCardTitle(val cardTitle: String) : UiAction()
        data object ShowBottomSheet : UiAction()
        data object HideBottomSheet : UiAction()
        data object AddCreditCard : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
