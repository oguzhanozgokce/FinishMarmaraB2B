package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.PostProductBasketUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.DeleteFavoriteProductUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetFavoriteProductsUseCase
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
    private val postProductBasketUseCase: PostProductBasketUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadFavoriteProducts -> loadFavoriteProducts()
            is UiAction.DeleteFavorite -> deleteFavorite(uiAction.productId)
            is UiAction.PostProductBasket -> postProductBasket(uiAction.productId)
            is UiAction.ToggleSelectedTabIndex -> updateState { copy(selectedTabIndex = uiAction.tabIndex) }
            is UiAction.OnChangeCollectionName -> updateState { copy(collectionName = uiAction.collectionName) }
            is UiAction.ShowBottomSheet -> showBottomSheet()
            is UiAction.HideBottomSheet -> hideBottomSheet()
        }
    }

    private fun showBottomSheet() = updateState { copy(isShowBottomSheet = true) }
    private fun hideBottomSheet() = updateState { copy(isShowBottomSheet = false) }

    private fun deleteFavorite(productId: Int) {
        viewModelScope.launch {
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
    }

    private fun loadFavoriteProducts() {
        viewModelScope.launch {
            val flow = getFavoriteProductsUseCase()
                .cachedIn(viewModelScope)
            updateState { copy(favoriteList = flow) }
        }
    }

    private fun postProductBasket(productId: Int) {
        viewModelScope.launch {
            postProductBasketUseCase(productId).fold(
                onSuccess = {
                    emitUiEffect(UiEffect.ShowToast("Product added to basket"))
                },
                onError = { error ->
                    updateState { copy(error = error) }
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }
}
