package com.oguzhanozgokce.finishmarmarab2b.ui.editprofile

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onFailure
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onSuccess
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toIsoUtcDateString
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.GetUserUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.PutUserUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.editprofile.EditProfileContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.editprofile.EditProfileContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.editprofile.EditProfileContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val putUserUseCase: PutUserUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        getUser()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.OnChangeName -> updateState { copy(name = uiAction.name) }
            is UiAction.OnChangeSurname -> updateState { copy(surname = uiAction.surname) }
            is UiAction.OnChangeEmail -> updateState { copy(email = uiAction.email) }
            is UiAction.OnChangePhoneNumber -> updateState { copy(phoneNumber = uiAction.phoneNumber) }
            is UiAction.OnChangeBirthDate -> updateState { copy(birthDate = uiAction.birthDate) }
            is UiAction.SaveClick -> saveUser()
        }
    }

    private fun getUser() {
        getUserUseCase()
            .onStart { updateState { copy(isLoading = true) } }
            .onCompletion { updateState { copy(isLoading = false) } }
            .onEach { resource ->
                resource.onSuccess { user ->
                    updateState {
                        copy(
                            user = user,
                            name = user.name,
                            surname = user.surname,
                            email = user.email,
                            phoneNumber = user.phoneNumber.drop(1),
                            birthDate = user.birthDate.toString()
                        )
                    }
                }
                resource.onFailure { error ->
                    updateState { copy(error = error) }
                    UiEffect.ShowToast(error)
                }
            }.launchIn(viewModelScope)
    }

    private fun saveUser() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            putUserUseCase(
                name = currentState.name,
                surname = currentState.surname,
                email = currentState.email,
                phoneNumber = "0${currentState.phoneNumber}",
                birthDate = currentState.birthDate.toIsoUtcDateString()
            ).fold(
                onSuccess = {
                    updateState { copy(isLoading = false) }
                    UiEffect.ShowToast("Profile saved successfully")
                },
                onError = { error ->
                    updateState { copy(isLoading = false, error = error) }
                    UiEffect.ShowToast(error)
                }
            )
        }
    }
}
