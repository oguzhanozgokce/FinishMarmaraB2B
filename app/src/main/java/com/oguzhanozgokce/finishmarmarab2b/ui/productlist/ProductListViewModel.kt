package com.oguzhanozgokce.finishmarmarab2b.ui.productlist

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ui.productlist.ProductListContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.productlist.ProductListContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.productlist.ProductListContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor() : MVI<UiState, UiAction, UiEffect>(UiState()) {

    fun onAction(uiAction: UiAction) {
    }
}
