package com.oguzhanozgokce.finishmarmarab2b.ui.payment

object PaymentContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
    )

    sealed class UiAction

    sealed class UiEffect
}
