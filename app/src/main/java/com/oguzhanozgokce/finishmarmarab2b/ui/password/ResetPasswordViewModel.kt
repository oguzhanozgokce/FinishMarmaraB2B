package com.oguzhanozgokce.finishmarmarab2b.ui.password

import com.oguzhanozgokce.finishmarmarab2b.core.presentation.delegation.UiHandler
import com.oguzhanozgokce.finishmarmarab2b.ui.password.ResetPasswordContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.password.ResetPasswordContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.password.ResetPasswordContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor() : UiHandler<UiState, UiEffect>(UiState()) {

    fun onAction(uiAction: UiAction) {
    }

}

