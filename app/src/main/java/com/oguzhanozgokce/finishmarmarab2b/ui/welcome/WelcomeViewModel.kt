package com.oguzhanozgokce.finishmarmarab2b.ui.welcome

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.UiHandler
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : UiHandler<UiState, UiEffect>(UiState()) {

    fun onAction(uiAction: UiAction) {

    }

}