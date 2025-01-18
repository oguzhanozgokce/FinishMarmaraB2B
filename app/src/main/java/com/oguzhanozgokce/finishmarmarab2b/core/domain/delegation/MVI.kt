package com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class MVI<UiState, UiEffect, UiAction>(
    initialState: UiState
) : ViewModel(), UiHandlerDelegate<UiState, UiEffect, UiAction> {

    private val _uiState = MutableStateFlow(initialState)
    private val _uiEffect = Channel<UiEffect>()

    override val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    override val uiEffect: Flow<UiEffect> = _uiEffect.receiveAsFlow()

    override fun updateState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    override suspend fun emitUiEffect(effect: UiEffect) {
        _uiEffect.send(effect)
    }

    override fun onAction(uiAction: UiAction) {}
}
