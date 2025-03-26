package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AnalyticsManager
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.PostProductBasketUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.DeleteCollectionUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.DeleteFavoriteProductUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetCollectionsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetFavoriteProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.PostCollectionUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteProductUseCase,
    private val postProductBasketUseCase: PostProductBasketUseCase,
    private val getCollectionsUseCase: GetCollectionsUseCase,
    private val postCollectionUseCase: PostCollectionUseCase,
    private val deleteCollectionUseCase: DeleteCollectionUseCase,
    private val analyticsManager: AnalyticsManager
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        getCollection()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadFavoriteProducts -> loadFavoriteProducts()
            is UiAction.LoadCollections -> getCollection()
            is UiAction.DeleteFavorite -> deleteFavorite(uiAction.productId)
            is UiAction.PostProductBasket -> postProductBasket(
                uiAction.productId,
                uiAction.productName
            )

            is UiAction.PostCollection -> postCollection(uiAction.collectionName)
            is UiAction.ToggleSelectedTabIndex -> updateState { copy(selectedTabIndex = uiAction.tabIndex) }
            is UiAction.OnChangeCollectionName -> updateState { copy(collectionName = uiAction.collectionName) }
            is UiAction.DeleteCollection -> deleteCollection(uiAction.collectionId)
            is UiAction.ShowBottomSheet -> showBottomSheet()
            is UiAction.HideBottomSheet -> hideBottomSheet()
        }
    }

    private fun showBottomSheet() = updateState { copy(isShowBottomSheet = true) }
    private fun hideBottomSheet() = updateState { copy(isShowBottomSheet = false) }

    private fun deleteFavorite(productId: Int) = viewModelScope.launch {
        deleteFavoriteUseCase(productId).fold(
            onSuccess = {
                emitUiEffect(UiEffect.Refresh)
            },
            onError = { error ->
                updateState { copy(error = error) }
                UiEffect.ShowToast(error)
            }
        )
    }

    private fun loadFavoriteProducts() {
        viewModelScope.launch {
            val flow = getFavoriteProductsUseCase()
                .cachedIn(viewModelScope)
            updateState { copy(favoriteList = flow) }
        }
    }

    private fun postProductBasket(productId: Int, productName: String) = viewModelScope.launch {
        postProductBasketUseCase(productId).fold(
            onSuccess = {
                emitUiEffect(UiEffect.ShowToast("Product added to basket"))
                analyticsManager.logProductAddedToCart(productId, productName)
            },
            onError = { error ->
                updateState { copy(error = error) }
                emitUiEffect(UiEffect.ShowToast(error))
            }
        )
    }

    private fun getCollection() = viewModelScope.launch {
        updateState { copy(isLoading = true) }
        getCollectionsUseCase().fold(
            onSuccess = { collectionList ->
                updateState { copy(collectionList = collectionList, isLoading = false) }
            },
            onError = { error ->
                updateState { copy(error = error, isLoading = false) }
                emitUiEffect(UiEffect.ShowToast(error))
            }
        )
    }

    private fun postCollection(collectionName: String) = viewModelScope.launch {
        postCollectionUseCase(collectionName).fold(
            onSuccess = { id ->
                updateState { copy(isRefreshCollection = true, collectionName = "") }
                hideBottomSheet()
                emitUiEffect(UiEffect.ShowToast("Collection created"))
                emitUiEffect(UiEffect.NavigateToSelectedFavorite(collectionName, id))
            },
            onError = { error ->
                updateState { copy(error = error) }
                emitUiEffect(UiEffect.ShowToast(error))
            }
        )
    }

    private fun deleteCollection(collectionId: Int) = viewModelScope.launch {
        deleteCollectionUseCase(collectionId).fold(
            onSuccess = { response ->
                updateState {
                    copy(
                        collectionList = collectionList.filterNot { it.id == response.collectionId }
                    )
                }
            },
            onError = { error ->
                updateState { copy(error = error) }
                emitUiEffect(UiEffect.ShowToast(error))
            }
        )
    }
}
