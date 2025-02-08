package com.oguzhanozgokce.finishmarmarab2b.ui.payment

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product

object PaymentContract {
    data class UiState(
        val isLoading: Boolean = false,
        val products: List<Product> = emptyList(),
        val totalPrice: Double = 0.0,
        val showDialog: Boolean = false
    )

    sealed class UiAction {
        data object HideDialog : UiAction()
        data object ShowDialog : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
