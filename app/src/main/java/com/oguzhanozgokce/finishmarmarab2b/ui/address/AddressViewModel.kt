package com.oguzhanozgokce.finishmarmarab2b.ui.address

import androidx.lifecycle.ViewModel
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ui.address.AddressContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.address.AddressContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.address.AddressContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
) : MVI<UiState, UiEffect, UiAction>(UiState()) {


    override fun onAction(uiAction: UiAction) {
    }
}

