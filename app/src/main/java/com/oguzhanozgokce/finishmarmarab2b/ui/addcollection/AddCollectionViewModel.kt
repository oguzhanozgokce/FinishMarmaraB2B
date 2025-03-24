package com.oguzhanozgokce.finishmarmarab2b.ui.addcollection

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ui.addcollection.AddCollectionContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.addcollection.AddCollectionContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.addcollection.AddCollectionContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddCollectionViewModel @Inject constructor() : MVI<UiState, UiEffect, UiAction>(UiState()) {


    override fun onAction(uiAction: UiAction) {
    }
}

