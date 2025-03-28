package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.core.domain.evet.EventBus
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Collection
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AnalyticsManager
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.PostProductBasketUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.DeleteCollectionUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.DeleteFavoriteProductUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetCollectionsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetFavoriteProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.PostCollectionAddProductUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.PostCollectionUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.PutCollectionUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
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
    private val putCollectionUseCase: PutCollectionUseCase,
    private val postCollectionAddProductUseCase: PostCollectionAddProductUseCase,
    private val analyticsManager: AnalyticsManager
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        getCollection()
        observeEvents()
    }

    private fun observeEvents() {
        viewModelScope.launch {
            EventBus.eventFlow.collect { event ->
                when (event) {
                    is EventBus.Event.LoadCollection -> getCollection()
                }
            }
        }
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
            is UiAction.UpdateCollection -> updateCollection(
                uiAction.collectionId,
                uiAction.collectionName
            )

            is UiAction.HideBottomSheet -> hideBottomSheet()
            is UiAction.ShowBottomSheet -> showBottomSheet()
            is UiAction.ShowUpdateBottomSheet -> showUpdateCollectionBS(
                collectionId = uiAction.collectionId,
                collectionName = uiAction.collectionName
            )

            is UiAction.ShowProductToCollectionBottomSheet -> showProductToCollection(uiAction.productId)
            is UiAction.AddProductToCollection -> addProductToCollection()
            is UiAction.OnChangeProductToCollection -> toggleProductToCollection(uiAction.collection)
        }
    }

    private fun toggleProductToCollection(collection: Collection) {
        updateState {
            copy(
                selectedCollection = if (selectedCollection == collection) null else collection
            )
        }
    }

    private fun showBottomSheet() = updateState { copy(isShowBottomSheet = true) }
    private fun hideBottomSheet() =
        updateState {
            copy(
                isShowBottomSheet = false,
                isShowUpdateBS = false,
                collectionName = "",
                isShowProductToCollectionBS = false,
                selectedCollection = null,
                selectedProductId = 0,
            )
        }

    private fun showProductToCollection(productId: Int) {
        updateState {
            copy(
                isShowProductToCollectionBS = true,
                selectedProductId = productId
            )
        }
    }

    private fun showUpdateCollectionBS(collectionId: Int, collectionName: String) = updateState {
        copy(
            isShowUpdateBS = true,
            collectionId = collectionId,
            collectionName = collectionName
        )
    }

    private fun deleteFavorite(productId: Int) = viewModelScope.launch {
        deleteFavoriteUseCase(productId).fold(
            onSuccess = {
                updateState {
                    copy(
                        favoriteList = favoriteList.map { pagingData ->
                            pagingData.filter { it.id != productId }
                        }
                    )
                }
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

    private fun updateCollection(collectionId: Int, collectionName: String) =
        viewModelScope.launch {
            putCollectionUseCase(collectionId, collectionName).fold(
                onSuccess = {
                    updateCollectionInState(collectionId, collectionName)
                    hideBottomSheet()
                    emitUiEffect(UiEffect.ShowToast("Collection Updated"))
                },
                onError = { error ->
                    updateState { copy(error = error) }
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }

    private fun updateCollectionInState(collectionId: Int, newName: String) = updateState {
        copy(
            collectionList = collectionList.map { collection ->
                if (collection.id == collectionId) collection.copy(name = newName) else collection
            },
            collectionName = ""
        )
    }

    private fun addProductToCollection() = viewModelScope.launch {
        currentState.selectedCollection?.let {
            postCollectionAddProductUseCase(
                productId = currentState.selectedProductId,
                collectionId = it.id
            ).fold(
                onSuccess = {
                    emitUiEffect(UiEffect.ShowToast("Product added to collection"))
                    hideBottomSheet()
                },
                onError = { error ->
                    updateState { copy(error = error) }
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }
}
