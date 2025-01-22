package com.oguzhanozgokce.finishmarmarab2b.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onFailure
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onSuccess
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.GetUserUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.AddProductToFavoritesUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.DeleteFavoriteProductUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserCase: GetUserUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductToFavoritesUseCase: AddProductToFavoritesUseCase,
    private val deleteFavoriteProductUseCase: DeleteFavoriteProductUseCase,
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        onAction(UiAction.LoadGetUser)
        onAction(UiAction.FetchProduct)
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadGetUser -> loadGetUser()
            is UiAction.FetchCategory -> fetchCategory()
            is UiAction.FetchProduct -> fetchProduct()
            is UiAction.ToggleFavorite -> toggleFavorite(uiAction.productId, uiAction.isFavorite)
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

    private fun fetchCategory() {
    }

    private fun addProductToFavorites(productId: Int) {
        addProductToFavoritesUseCase(productId)
            .onStart { updateState { copy(isLoading = true) } }
            .onCompletion { updateState { copy(isLoading = false) } }
            .onEach { resource ->
                resource.onSuccess {
                    updateState { copy(isLoading = false) }
                    UiEffect.ShowToast("Product added to favorites")
                }
                resource.onFailure { error ->
                    updateState { copy(error = error) }
                    UiEffect.ShowToast(error)
                }
            }.launchIn(viewModelScope)
    }

    private fun deleteFavoriteProduct(productId: Int) {
        deleteFavoriteProductUseCase(productId)
            .onStart { updateState { copy(isLoading = true) } }
            .onCompletion { updateState { copy(isLoading = false) } }
            .onEach { resource ->
                resource.onSuccess {
                    updateState { copy(isLoading = false) }
                    UiEffect.ShowToast("Product removed from favorites")
                }.onFailure { error ->
                    updateState { copy(error = error) }
                    UiEffect.ShowToast(error)
                }
            }.launchIn(viewModelScope)
    }

    private fun fetchProduct() {
        updateState { copy(isLoading = true) }
        val flow = getProductsUseCase()
            .cachedIn(viewModelScope)
        updateState { copy(productFlow = flow, isLoading = false) }
    }

    private fun toggleFavorite(productId: Int, isFavorite: Boolean) {
        if (isFavorite) {
            deleteFavoriteProduct(productId)
        } else {
            addProductToFavorites(productId)
        }
    }
}
