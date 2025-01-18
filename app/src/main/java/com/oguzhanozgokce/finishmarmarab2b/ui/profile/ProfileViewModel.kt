package com.oguzhanozgokce.finishmarmarab2b.ui.profile

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onFailure
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onSuccess
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.GetUserUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.ProfileContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.ProfileContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.ProfileContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        getUser()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.GetUser -> getUser()
        }
    }

    private fun getUser() {
        getUserUseCase()
            .onStart { updateState { copy(isLoading = true) } }
            .onCompletion { updateState { copy(isLoading = false) } }
            .onEach { resource ->
                resource.onSuccess { user ->
                    updateState { copy(user = user) }
                }
                .onFailure { message ->
                    updateState { copy(error = message) }
                    emitUiEffect(UiEffect.ShowToast(message))
                }
            }.launchIn(viewModelScope)
    }
}