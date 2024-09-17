package com.oguzhanozgokce.finishmarmarab2b.ui.signup

object SignupContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
    )

    sealed class UiAction

    sealed class UiEffect
}