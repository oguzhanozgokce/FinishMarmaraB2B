package com.oguzhanozgokce.finishmarmarab2b.ui.signup

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.UiHandler
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : UiHandler<UiState, UiEffect>(UiState()) {

    fun onAction(uiAction: UiAction) {
        when(uiAction){
            is UiAction.NameChanged ->  updateState { copy(name = uiAction.name) }
            is UiAction.SurnameChanged -> updateState { copy(surname = uiAction.surname) }
            is UiAction.EmailChanged -> updateState { copy(email = uiAction.email) }
            is UiAction.PasswordChanged -> updateState { copy(password = uiAction.password) }
            is UiAction.Signup -> signup()
            is UiAction.ClearError -> updateState { copy(error = "") }
        }
    }

    private fun signup() {

    }
}