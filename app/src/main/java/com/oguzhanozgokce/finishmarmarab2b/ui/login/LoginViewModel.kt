package com.oguzhanozgokce.finishmarmarab2b.ui.login

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.UiHandler
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : UiHandler<UiState, UiEffect>(UiState()) {

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.EmailChanged -> updateState { copy(email = uiAction.email) }
            is UiAction.PasswordChanged -> updateState { copy(password = uiAction.password) }
            is UiAction.LoginClicked -> login()
            is UiAction.ClearError -> updateState { copy(error = "") }
        }
    }

    private fun login() {

    }
}