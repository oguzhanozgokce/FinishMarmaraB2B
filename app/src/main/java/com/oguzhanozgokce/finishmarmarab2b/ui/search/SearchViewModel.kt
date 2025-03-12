package com.oguzhanozgokce.finishmarmarab2b.ui.search

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.DeleteAllSearchHistoryUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.DeleteSearchHistoryUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetSearchHistoryUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetSearchProductUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetTop5ProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getTop5ProductsUseCase: GetTop5ProductsUseCase,
    private val getSearchProductUseCase: GetSearchProductUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val deleteSearchHistoryUseCase: DeleteSearchHistoryUseCase,
    private val deleteAllSearchHistoryUseCase: DeleteAllSearchHistoryUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        loadTop5Products()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.OnSearchValueChange -> updateState { copy(searchValue = uiAction.value) }
            is UiAction.OnSearch -> searchProduct(uiAction.searchQuery)
            is UiAction.DeleteSearchHistory -> deleteSearchHistory(uiAction.id)
            is UiAction.LoadSearchHistory -> searchHistory()
            is UiAction.DeleteAllSearchHistory -> deleteAllSearchHistory()
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

    private fun searchProduct(searchQuery: String) {
        viewModelScope.launch {
            getSearchProductUseCase(searchQuery = searchQuery).fold(
                onSuccess = { productList ->
                    updateState { copy(top5productList = productList) }
                },
                onError = { error ->
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }

    private fun searchHistory() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getSearchHistoryUseCase().fold(
                onSuccess = { searchHistoryList ->
                    updateState { copy(searchHistoryList = searchHistoryList, isLoading = false) }
                },
                onError = { error ->
                    updateState { copy(isLoading = false) }
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }

    private fun deleteSearchHistory(id: Int) {
        viewModelScope.launch {
            deleteSearchHistoryUseCase(id).fold(
                onSuccess = {
                    val updatedList = currentState.searchHistoryList.filter { it.id != id }
                    updateState { copy(searchHistoryList = updatedList) }
                },
                onError = { error ->
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }

    private fun deleteAllSearchHistory() {
        viewModelScope.launch {
            deleteAllSearchHistoryUseCase().fold(
                onSuccess = {
                    updateState { copy(searchHistoryList = emptyList()) }
                },
                onError = { error ->
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }
}
