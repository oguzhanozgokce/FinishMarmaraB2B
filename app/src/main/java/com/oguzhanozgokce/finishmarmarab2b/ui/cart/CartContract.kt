package com.oguzhanozgokce.finishmarmarab2b.ui.cart

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product

object CartContract {
    data class UiState(
        val isLoading: Boolean = false,
        val basketProducts: List<Product> = emptyList(),
        val totalPrice: Double = 0.0
    )

    sealed class UiAction {
        data class DeleteBasketProduct(val productId: Int) : UiAction()
        data object DeleteBasketAll : UiAction()
        data class PostProductBasket(val productId: Int) : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
