package com.oguzhanozgokce.finishmarmarab2b.ui.selectedfavorite

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.cachedIn
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostCollectionAddProductsRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AnalyticsManager
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetFavoriteProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.PostCollectionAddProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.navigation.SelectedFavorite
import com.oguzhanozgokce.finishmarmarab2b.ui.selectedfavorite.SelectedFavoriteContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.selectedfavorite.SelectedFavoriteContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.selectedfavorite.SelectedFavoriteContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectedFavoriteViewModel @Inject constructor(
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val postCollectionAddProductsUseCase: PostCollectionAddProductsUseCase,
    private val analyticsManager: AnalyticsManager,
    savedStateHandle: SavedStateHandle
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    private val args = savedStateHandle.toRoute<SelectedFavorite>()
    private val collectionId = args.collectionId

    init {
        loadFavoriteProducts()
        updateState { copy(collectionName = collectionName) }
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.ToggleProductSelection -> toggleProductSelection(uiAction.productId)
            is UiAction.AddProductToCollection -> addProductsToCollection()
        }
    }

    private fun toggleProductSelection(productId: Int) {
        val current = uiState.value.selectedProductIds.toMutableSet()
        if (current.contains(productId)) {
            current.remove(productId)
        } else {
            current.add(productId)
        }
        updateState { copy(selectedProductIds = current) }
    }

    private fun loadFavoriteProducts() {
        viewModelScope.launch {
            val flow = getFavoriteProductsUseCase()
                .cachedIn(viewModelScope)
            updateState { copy(favoriteList = flow) }
        }
    }

    private fun addProductsToCollection() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            val selectedProductIds = currentState.selectedProductIds
            if (selectedProductIds.isEmpty()) {
                emitUiEffect(UiEffect.ShowToast("Please select at least one product."))
                return@launch
            }
            val requestList = selectedProductIds.map { productId ->
                PostCollectionAddProductsRequest(
                    collectionId = collectionId,
                    productId = productId
                )
            }
            postCollectionAddProductsUseCase(requestList).fold(
                onSuccess = {
                    updateState { copy(isLoading = false) }
                    emitUiEffect(UiEffect.ShowToast("Successful"))
                    emitUiEffect(UiEffect.NavigateToBack)
                    analyticsManager.logAddCollection(args.collectionId, args.collectionName)
                },
                onError = { errorMessage ->
                    updateState { copy(isLoading = false) }
                    emitUiEffect(UiEffect.ShowToast("Failed to add products to collection: $errorMessage"))
                }
            )
        }
    }
}
