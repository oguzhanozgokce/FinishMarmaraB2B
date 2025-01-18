package com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UiHandlerDelegate<UiState, UiEffect, UiAction> {
    val uiState: StateFlow<UiState>
    val uiEffect: Flow<UiEffect>

    val currentState: UiState
        get() = uiState.value

    fun updateState(block: UiState.() -> UiState)
    suspend fun emitUiEffect(effect: UiEffect)

    fun onAction(uiAction: UiAction)
}
