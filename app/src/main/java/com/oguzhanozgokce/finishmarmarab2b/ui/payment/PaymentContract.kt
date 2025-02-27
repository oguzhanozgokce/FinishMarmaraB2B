package com.oguzhanozgokce.finishmarmarab2b.ui.payment

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Province

object PaymentContract {
    data class UiState(
        val isLoading: Boolean = false,
        val products: List<Product> = emptyList(),
        val totalPrice: Double = 0.0,
        val showDialog: Boolean = false,
        val cities: List<Province> = emptyList(),
        val districts: List<String> = emptyList(),
        val errorMessage: String? = null
    )

    sealed class UiAction {
        data object HideDialog : UiAction()
        data object ShowDialog : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
