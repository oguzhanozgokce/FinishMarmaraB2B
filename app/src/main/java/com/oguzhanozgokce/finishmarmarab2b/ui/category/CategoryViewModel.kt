package com.oguzhanozgokce.finishmarmarab2b.ui.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetCategoryProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.category.CategoryContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.category.CategoryContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.category.CategoryContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoryProductsUseCase: GetCategoryProductsUseCase,
    private val savedStateHandle: SavedStateHandle
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    private val args = savedStateHandle.toRoute<Screen.CategoryProducts>()

    init {
        updateState { copy(categoryName = args.name) }
        loadCategoryProducts(args.id)
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadCategoryProducts -> loadCategoryProducts(uiAction.categoryId)
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
                            categoryProducts = categoryProducts.list.orEmpty()
                        )
                    }
                },
                onError = { updateState { copy(isLoading = false) } }
            )
        }
    }
}
