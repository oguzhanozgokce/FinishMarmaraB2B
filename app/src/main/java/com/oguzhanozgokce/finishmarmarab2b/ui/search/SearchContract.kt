package com.oguzhanozgokce.finishmarmarab2b.ui.search

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.SearchHistory

object SearchContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val searchHistoryList: List<SearchHistory> = emptyList(),
        val top5productList: List<Product> = emptyList(),
        val searchValue: String = "",
    )

    sealed class UiAction {
        data class OnSearchValueChange(val value: String) : UiAction()
        data class OnSearch(val searchQuery: String) : UiAction()
        data class DeleteSearchHistory(val id: Int) : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
