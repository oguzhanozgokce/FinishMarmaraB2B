package com.oguzhanozgokce.finishmarmarab2b.ui.signup

import androidx.lifecycle.ViewModel
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: UiAction) {
        when(uiAction){
            is UiAction.NameChanged -> updateUiState { copy(name = uiAction.name) }
            is UiAction.SurnameChanged -> updateUiState { copy(surname = uiAction.surname) }
            is UiAction.EmailChanged -> updateUiState { copy(email = uiAction.email) }
            is UiAction.PasswordChanged -> updateUiState { copy(password = uiAction.password) }
            is UiAction.Signup -> signup()
            is UiAction.ClearError -> updateUiState { copy(error = "") }
        }
    }

    private fun signup() {

    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}