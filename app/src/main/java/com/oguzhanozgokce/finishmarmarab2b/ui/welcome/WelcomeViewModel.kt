package com.oguzhanozgokce.finishmarmarab2b.ui.welcome

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : MVI<UiState, UiEffect, UiAction>(UiState()) {

    override fun onAction(uiAction: UiAction) {
    }
}
