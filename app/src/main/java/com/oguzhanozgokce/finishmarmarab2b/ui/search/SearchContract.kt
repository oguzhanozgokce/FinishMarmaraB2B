package com.oguzhanozgokce.finishmarmarab2b.ui.search

object SearchContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val historyList: List<HistorySearch> = emptyList(),
        val searchValue: String = ""
    )

    sealed class UiAction {
        data class OnSearchValueChange(val value: String) : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
