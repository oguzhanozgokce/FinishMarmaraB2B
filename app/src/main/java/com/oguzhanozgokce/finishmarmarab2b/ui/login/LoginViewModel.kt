package com.oguzhanozgokce.finishmarmarab2b.ui.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onFailure
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onSuccess
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.UiHandler
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.GetEmailUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.SaveOrUpdateEmailUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.SignInUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val saveOrUpdateEmailUseCase: SaveOrUpdateEmailUseCase,
    private val getEmailUseCase: GetEmailUseCase
) : UiHandler<UiState, UiEffect>(UiState()) {

    init {
        onAction(UiAction.GetEmail)
    }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.GetEmail -> loadSavedEmail()
            is UiAction.EmailChanged -> updateState { copy(email = uiAction.email) }
            is UiAction.PasswordChanged -> updateState { copy(password = uiAction.password) }
            is UiAction.LoginClicked -> login()
            is UiAction.ClearError -> updateState { copy(error = "") }
        }
    }

    private fun login() {
        val email = uiState.value.email
        val password = uiState.value.password
        val signInRequest = SignInRequest(
            email = email,
            password = password
        )
        signInUseCase(signInRequest)
            .onStart { updateState { copy( isLoading = true) } }
            .onCompletion { updateState { copy( isLoading = false) } }
            .onEach { resource ->
                resource.onSuccess {
                    saveEmail(email)
                    emitUiEffect(UiEffect.GoToHome)
                    emitUiEffect(UiEffect.ShowToast("Successful"))
                }
                resource.onFailure { message ->
                    updateState { copy(error = message) }
                    emitUiEffect(UiEffect.ShowToast(message))
                    Log.e("LoginViewModel", "Error: $message")
                }
            }.launchIn(viewModelScope)
    }

    private fun saveEmail(email: String) {
        viewModelScope.launch {
            saveOrUpdateEmailUseCase(email)
        }
    }

    private fun loadSavedEmail() {
        viewModelScope.launch {
            val savedEmail = getEmailUseCase() ?: ""
            updateState { copy(email = savedEmail) }
        }
    }
}