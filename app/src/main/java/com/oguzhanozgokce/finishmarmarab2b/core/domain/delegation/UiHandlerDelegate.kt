package com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UiHandlerDelegate<UiState, UiEffect> {
    val uiState: StateFlow<UiState>
    val uiEffect: Flow<UiEffect>

    fun updateState(block: UiState.() -> UiState)
    suspend fun emitUiEffect(effect: UiEffect)
}
