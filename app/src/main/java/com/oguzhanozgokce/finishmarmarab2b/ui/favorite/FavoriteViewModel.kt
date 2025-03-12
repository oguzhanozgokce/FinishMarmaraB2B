package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onFailure
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onSuccess
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.GetUserUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.PostProductBasketUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.DeleteFavoriteProductUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetFavoriteProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getUserCase: GetUserUseCase,
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteProductUseCase,
    private val postProductBasketUseCase: PostProductBasketUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        loadGetUser()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadFavoriteProducts -> loadFavoriteProducts()
            is UiAction.LoadGetUser -> loadGetUser()
            is UiAction.DeleteFavorite -> deleteFavorite(uiAction.productId)
            is UiAction.PostProductBasket -> postProductBasket(uiAction.productId)
        }
    }

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

    private fun loadGetUser() {
        getUserCase()
            .onStart { updateState { copy(isLoading = true) } }
            .onCompletion { updateState { copy(isLoading = false) } }
            .onEach { resource ->
                resource.onSuccess { user ->
                    updateState { copy(user = user) }
                }
                resource.onFailure { error ->
                    updateState { copy(error = error) }
                    UiEffect.ShowToast(error)
                }
            }.launchIn(viewModelScope)
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
