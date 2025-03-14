package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreditCardViewModel @Inject constructor() : MVI<UiState, UiAction, UiEffect>(UiState()) {

    override fun onAction(uiAction: UiEffect) {
        super.onAction(uiAction)
    }
}

