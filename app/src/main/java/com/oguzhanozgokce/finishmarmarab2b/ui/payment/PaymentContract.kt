package com.oguzhanozgokce.finishmarmarab2b.ui.payment

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.City
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product

object PaymentContract {
    data class UiState(
        val isLoading: Boolean = false,
        val products: List<Product> = emptyList(),
        val totalPrice: Double = 0.0,
        val showDialog: Boolean = false,
        val cities: List<City> = emptyList(),
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
