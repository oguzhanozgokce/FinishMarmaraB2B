package com.oguzhanozgokce.finishmarmarab2b.ui.password

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ui.password.ResetPasswordContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.password.ResetPasswordContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.password.ResetPasswordContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor() : MVI<UiState, UiEffect, UiAction>(UiState()) {

    override fun onAction(uiAction: UiAction) {
    }

}

