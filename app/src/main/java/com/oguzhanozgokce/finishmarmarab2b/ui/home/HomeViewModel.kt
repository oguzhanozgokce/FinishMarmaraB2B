package com.oguzhanozgokce.finishmarmarab2b.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.oguzhanozgokce.finishmarmarab2b.core.common.Constant
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onFailure
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onSuccess
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.GetUserUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetCategoriesUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.ToggleFavoriteUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MIN_CLICK_INTERVAL = 500L

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserCase: GetUserUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    private var lastClickTime = 0L

    init {
        fetchCategory()
        loadGetUser()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadGetUser -> loadGetUser()
            is UiAction.FetchCategory -> fetchCategory()
            is UiAction.FetchProduct -> fetchProduct()
            is UiAction.ToggleFavorite -> toggleFavorite(uiAction.productId)
        }
    }

    private fun fetchProduct() {
        viewModelScope.launch {
            updateState { copy(isProductLoading = true) }
            getProductsUseCase(Constant.PAGE_LIMIT).fold(
                onSuccess = { paginationData ->
                    updateState {
                        copy(
                            productList = paginationData.list.orEmpty(),
                            isProductLoading = false
                        )
                    }
                },
                onError = { error ->
                    updateState { copy(error = error, isProductLoading = false) }
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }

    private fun toggleFavorite(productId: Int) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime < MIN_CLICK_INTERVAL) {
            return
        }
        lastClickTime = currentTime
        viewModelScope.launch {
            toggleFavoriteUseCase(productId)
                .onSuccess { response ->
                    updateProductFavoriteStatus(response.productId, response.isFavorite)
                }.onFailure {
                    UiEffect.ShowToast(it)
                }
        }
    }

    private fun updateProductFavoriteStatus(productId: Int, isFavorite: Boolean) {
        updateState {
            val updatedProducts = productList.map { product ->
                if (product.id == productId) product.copy(isFavorite = isFavorite) else product
            }
            copy(productList = updatedProducts)
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
        updateState { copy(isCategoryLoading = true) }
        val flow = getCategoriesUseCase()
            .cachedIn(viewModelScope)
        updateState { copy(categoryFlow = flow, isCategoryLoading = false) }
    }
}
