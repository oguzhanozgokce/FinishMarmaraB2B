package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart

object CreditCardContract {
    data class UiState(
        val isLoading: Boolean = false,
        val creditCardList: List<CreditCart> = emptyList(),
        val error: String = "",
    )

    sealed class UiAction {

    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
