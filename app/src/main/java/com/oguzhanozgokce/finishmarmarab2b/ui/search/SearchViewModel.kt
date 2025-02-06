package com.oguzhanozgokce.finishmarmarab2b.ui.search

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetTop5ProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getTop5ProductsUseCase: GetTop5ProductsUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        loadTop5Products()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.OnSearchValueChange -> {
                updateState { copy(searchValue = uiAction.value) }
            }
        }
    }

    private fun loadTop5Products() {
        viewModelScope.launch {
            getTop5ProductsUseCase().fold(
                onSuccess = { productList ->
                    updateState { copy(top5productList = productList) }
                },
                onError = { error ->
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }
}
