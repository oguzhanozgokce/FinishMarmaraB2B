package com.oguzhanozgokce.finishmarmarab2b.ui.selectedfavorite

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

object SelectedFavoriteContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val favoriteList: Flow<PagingData<Product>> = emptyFlow(),
        val selectedProductIds: Set<Int> = emptySet(),
        val collectionName: String = "",
    )

    sealed class UiAction {
        data class ToggleProductSelection(val productId: Int) : UiAction()
        data object AddProductToCollection : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
        data object NavigateToBack : UiEffect()
    }
}
