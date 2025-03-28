package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Collection
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

object FavoriteContract {
    data class UiState(
        val isLoading: Boolean = false,
        val favoriteList: Flow<PagingData<Product>> = emptyFlow(),
        var selectedTabIndex: Int = 0,
        var collectionName: String = "",
        val collectionId: Int = 0,
        val isShowBottomSheet: Boolean = false,
        val isShowUpdateBS: Boolean = false,
        val isShowProductToCollectionBS: Boolean = false,
        val selectedCollection: Collection? = null,
        val selectedProductId: Int = 0,
        val collectionList: List<Collection> = emptyList(),
        val error: String? = "",
        val isRefreshCollection: Boolean = false,
    )

    sealed class UiAction {
        data object LoadFavoriteProducts : UiAction()
        data object LoadCollections : UiAction()
        data class DeleteFavorite(val productId: Int) : UiAction()
        data class PostProductBasket(val productId: Int, val productName: String) : UiAction()
        data class PostCollection(val collectionName: String) : UiAction()
        data class ToggleSelectedTabIndex(val tabIndex: Int) : UiAction()
        data class OnChangeCollectionName(val collectionName: String) : UiAction()
        data class DeleteCollection(val collectionId: Int) : UiAction()
        data class UpdateCollection(val collectionId: Int, val collectionName: String) : UiAction()
        data object ShowBottomSheet : UiAction()
        data object HideBottomSheet : UiAction()
        data class ShowUpdateBottomSheet(val collectionId: Int, val collectionName: String) :
            UiAction()

        data class ShowProductToCollectionBottomSheet(val productId: Int) : UiAction()
        data object AddProductToCollection : UiAction()
        data class OnChangeProductToCollection(val collection: Collection) : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
        data object Refresh : UiEffect()
        data class NavigateToSelectedFavorite(val collectionName: String, val collectionId: Int) :
            UiEffect()
    }
}
