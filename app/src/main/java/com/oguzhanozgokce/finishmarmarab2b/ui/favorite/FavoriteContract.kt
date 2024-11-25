package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.ProductUi

object FavoriteContract {
    data class UiState(
        val isLoading: Boolean = false,
        val favoriteList: List<ProductUi> = emptyList(),
    )

    sealed class UiAction

    sealed class UiEffect
}