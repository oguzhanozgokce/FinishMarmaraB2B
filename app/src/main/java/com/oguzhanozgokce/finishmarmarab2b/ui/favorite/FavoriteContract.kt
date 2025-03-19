package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

object FavoriteContract {
    data class UiState(
        val isLoading: Boolean = false,
        val favoriteList: Flow<PagingData<Product>> = emptyFlow(),
        var selectedTabIndex: Int = 0,
        val error: String? = "",
    )

    sealed class UiAction {
        data object LoadFavoriteProducts : UiAction()
        data class DeleteFavorite(val productId: Int) : UiAction()
        data class PostProductBasket(val productId: Int) : UiAction()
        data class ToggleSelectedTabIndex(val tabIndex: Int) : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
        data object Refresh : UiEffect()
    }
}
