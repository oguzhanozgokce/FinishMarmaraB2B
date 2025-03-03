package com.oguzhanozgokce.finishmarmarab2b.ui

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.domain.connectivity.ConnectivityListener
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ui.MainContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.MainContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.MainContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val connectivityListener: ConnectivityListener
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        listenConnectivity()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.CheckNetworkAgain -> {
                checkNetworkAgain()
            }
        }
    }

    private fun listenConnectivity() {
        viewModelScope.launch {
            connectivityListener.isNetworkAvailable.collect { isNetworkAvailable ->
                updateState {
                    copy(isShowNoNetworkDialog = !isNetworkAvailable)
                }
            }
        }
    }

    private fun checkNetworkAgain() {
        viewModelScope.launch {
            val isConnected = connectivityListener.isNetworkAvailable.first()
            updateState { copy(isShowNoNetworkDialog = !isConnected) }
        }
    }
}
