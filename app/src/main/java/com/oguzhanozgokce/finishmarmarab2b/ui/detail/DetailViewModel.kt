package com.oguzhanozgokce.finishmarmarab2b.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.UiHandler
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : UiHandler<UiState, UiEffect>(UiState()) {

    private val args = savedStateHandle.toRoute<Screen.Detail>()

    init {
       args.id
    }

    fun onAction(uiAction: UiAction) {
    }
}