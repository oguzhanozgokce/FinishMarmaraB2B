package com.oguzhanozgokce.finishmarmarab2b.ui.ordersuccess

object OrderSuccessContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
    )

    sealed class UiAction

    sealed class UiEffect {
        data object OnNavigateToHome : UiEffect()
    }
}
