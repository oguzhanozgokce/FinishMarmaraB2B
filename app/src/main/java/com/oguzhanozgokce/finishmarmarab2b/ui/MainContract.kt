package com.oguzhanozgokce.finishmarmarab2b.ui

object MainContract {
    data class UiState(
        val isShowNoNetworkDialog: Boolean = false,
    )

    sealed interface UiAction {
        data object CheckNetworkAgain : UiAction
    }

    sealed interface UiEffect
}
