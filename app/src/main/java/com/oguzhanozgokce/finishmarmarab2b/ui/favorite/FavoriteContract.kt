package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product

object FavoriteContract {
    data class UiState(
        val isLoading: Boolean = false,
        val favoriteList: List<Product> = emptyList(),
    )

    sealed class UiAction

    sealed class UiEffect
}