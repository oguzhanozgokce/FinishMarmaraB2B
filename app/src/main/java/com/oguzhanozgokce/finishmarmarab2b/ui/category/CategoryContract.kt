package com.oguzhanozgokce.finishmarmarab2b.ui.category

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product

object CategoryContract {
    data class UiState(
        val isLoading: Boolean = false,
        val categoryProducts: List<Product> = emptyList(),
        val categoryName: String = ""
    )

    sealed class UiAction {
        data class LoadCategoryProducts(val categoryId: Int, val name: String) : UiAction()
    }

    sealed class UiEffect
}
