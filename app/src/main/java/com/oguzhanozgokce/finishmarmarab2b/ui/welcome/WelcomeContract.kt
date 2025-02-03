package com.oguzhanozgokce.finishmarmarab2b.ui.welcome

object WelcomeContract {
    data class UiState(
        val isLoading: Boolean = false,
    )

    sealed class UiAction

    sealed class UiEffect
}
