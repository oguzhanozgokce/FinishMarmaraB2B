package com.oguzhanozgokce.finishmarmarab2b.ui.signup

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onFailure
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onSuccess
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.UiHandler
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AuthRepository
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.SaveOrUpdateEmailUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.SignUpUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val saveOrUpdateEmailUseCase: SaveOrUpdateEmailUseCase
) : UiHandler<UiState, UiEffect>(UiState()) {

    fun onAction(uiAction: UiAction) {
        when(uiAction){
            is UiAction.NameChanged ->  updateState { copy(name = uiAction.name) }
            is UiAction.SurnameChanged -> updateState { copy(surname = uiAction.surname) }
            is UiAction.EmailChanged -> updateState { copy(email = uiAction.email) }
            is UiAction.PasswordChanged -> updateState { copy(password = uiAction.password) }
            is UiAction.PhoneNumberChanged -> updateState { copy(phoneNumber = uiAction.phoneNumber) }
            is UiAction.Signup -> signup()
            is UiAction.ClearError -> updateState { copy(error = "") }
        }
    }

    private fun signup() {
        val name = uiState.value.name
        val surname = uiState.value.surname
        val email = uiState.value.email
        val password = uiState.value.password
        val phoneNumber = uiState.value.phoneNumber
        val signupRequest = SignUpRequest(
            name = name,
            surname = surname,
            email = email,
            password = password,
            phoneNumber = phoneNumber
        )
        signUpUseCase(signupRequest)
            .onStart { updateState { copy( isLoading = true) }  }
            .onCompletion { updateState { copy( isLoading = false) } }
            .onEach { resource ->
                resource.onSuccess {
                    saveEmail(email)
                    emitUiEffect(UiEffect.ShowToast("Success"))
                    emitUiEffect(UiEffect.GoToHome)
                }
                resource.onFailure { message ->
                    updateState { copy(error = message) }
                    emitUiEffect(UiEffect.ShowToast(message))
                }
            }.launchIn(viewModelScope)
    }

    private fun saveEmail(email: String) {
        viewModelScope.launch {
            saveOrUpdateEmailUseCase(email)
        }
    }
}