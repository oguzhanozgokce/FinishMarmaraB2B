package com.oguzhanozgokce.finishmarmarab2b.ui.products

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.oguzhanozgokce.finishmarmarab2b.core.common.Constant
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onFailure
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onSuccess
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AnalyticsManager
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetCategoryProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetSearchProductUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.ToggleFavoriteUseCase
import com.oguzhanozgokce.finishmarmarab2b.navigation.Products
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductsContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductsContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductsContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MIN_CLICK_INTERVAL = 500L

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getCategoryProductsUseCase: GetCategoryProductsUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val getSearchProductUseCase: GetSearchProductUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val analyticsManager: AnalyticsManager,
    savedStateHandle: SavedStateHandle,
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    private val args = savedStateHandle.toRoute<Products>()
    private val categoryId: Int? = args.id
    private val searchQuery: String = args.searchQuery ?: ""
    private val type: ProductListType = args.type
    private var lastClickTime = 0L

    init {
        updateState { copy(categoryName = categoryName) }
        fetchDataByType()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadCategoryProducts -> loadCategoryProducts(uiAction.categoryId)
            is UiAction.ToggleFavorite -> toggleFavorite(uiAction.productId)
        }
    }

    private fun fetchDataByType() {
        when (type) {
            ProductListType.ALL_PRODUCT -> fetchProduct()
            ProductListType.CATEGORY -> categoryId?.let { loadCategoryProducts(it) }
            ProductListType.SEARCH -> searchProduct()
        }
    }

    private fun loadCategoryProducts(categoryId: Int) {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getCategoryProductsUseCase(categoryId).fold(
                onSuccess = { categoryProducts ->
                    updateState {
                        copy(
                            isLoading = false,
                            categoryProducts = categoryProducts.list.orEmpty(),
                            typeList = categoryProducts.list.orEmpty()
                        )
                    }
                    analyticsManager.logCategoryViewed(categoryId)
                },
                onError = { updateState { copy(isLoading = false) } }
            )
        }
    }

    private fun fetchProduct() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            getProductsUseCase(Constant.MAX_PAGE_LIMIT).fold(
                onSuccess = { paginationData ->
                    updateState {
                        copy(
                            productList = paginationData.list.orEmpty(),
                            typeList = paginationData.list.orEmpty(),
                            isLoading = false
                        )
                    }
                },
                onError = { error ->
                    updateState { copy(error = error, isLoading = false) }
                }
            )
        }
    }

    private fun searchProduct() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            getSearchProductUseCase(searchQuery).fold(
                onSuccess = { paginationData ->
                    updateState {
                        copy(
                            productList = paginationData,
                            typeList = paginationData,
                            isLoading = false
                        )
                    }
                },
                onError = { error ->
                    updateState { copy(error = error, isLoading = false) }
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
                    HomeContract.UiEffect.ShowToast(it)
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
}
