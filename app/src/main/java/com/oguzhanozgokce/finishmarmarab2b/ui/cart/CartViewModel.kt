package com.oguzhanozgokce.finishmarmarab2b.ui.cart

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : MVI<UiState, UiEffect, UiAction>(UiState()) {

    override fun onAction(uiAction: UiAction) {
    }
}
