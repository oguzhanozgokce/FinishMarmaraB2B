package com.oguzhanozgokce.finishmarmarab2b.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onFailure
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onSuccess
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.UiHandler
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetUserResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.GetUserUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserCase: GetUserUseCase
) : UiHandler<UiState, UiEffect>(UiState()) {

    init {
        onAction(UiAction.LoadGetUser)
    }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.LoadGetUser -> loadGetUser()
            is UiAction.FetchCategory -> fetchCategory()
            is UiAction.FetchProduct -> fetchProduct()
            is UiAction.ToggleFavorite -> toggleFavorite()
        }
    }

    private fun loadGetUser() {
        getUserCase()
            .onStart { updateState { copy(isLoading = true) } }
            .onCompletion { updateState { copy(isLoading = false) } }
            .onEach { resource ->
                resource.onSuccess { user ->
                    Log.i("HomeViewModel", "User Loaded: $user")
                    updateState { copy(user = user) }
                }
                resource.onFailure { error ->
                    updateState { copy(error = error) }
                    UiEffect.ShowToast(error)
                }
            }.launchIn(viewModelScope)
    }

    private fun fetchCategory() {

    }

    private fun fetchProduct() {

    }

    private fun toggleFavorite() {

    }
}