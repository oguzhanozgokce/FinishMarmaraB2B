package com.oguzhanozgokce.finishmarmarab2b.ui.products

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.oguzhanozgokce.finishmarmarab2b.core.common.Constant
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetCategoryProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductsContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductsContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductsContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getCategoryProductsUseCase: GetCategoryProductsUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val savedStateHandle: SavedStateHandle
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    private val args = savedStateHandle.toRoute<Screen.Products>()
    private val categoryId: Int? = args.id
    private val categoryName: String = args.name ?: ""
    private val type: ProductListType = args.type

    init {
        updateState { copy(categoryName = categoryName) }
        fetchDataByType()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadCategoryProducts -> loadCategoryProducts(uiAction.categoryId)
        }
    }

    private fun fetchDataByType() {
        when (type) {
            ProductListType.ALL_PRODUCT -> fetchProduct()
            ProductListType.CATEGORY -> categoryId?.let { loadCategoryProducts(it) }
            ProductListType.SEARCH -> {}
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
}
