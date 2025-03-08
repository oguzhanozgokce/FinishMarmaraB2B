package com.oguzhanozgokce.finishmarmarab2b.ui.products

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product

object ProductsContract {
    data class UiState(
        val isLoading: Boolean = false,
        val categoryProducts: List<Product> = emptyList(),
        val productList: List<Product> = emptyList(),
        val typeList: List<Product> = emptyList(),
        val categoryName: String = "",
        val error: String = ""
    )

    sealed class UiAction {
        data class LoadCategoryProducts(val categoryId: Int, val name: String) : UiAction()
    }

    sealed class UiEffect
}
