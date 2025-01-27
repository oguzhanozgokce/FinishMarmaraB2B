package com.oguzhanozgokce.finishmarmarab2b.ui.category

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ui.category.CategoryContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.category.CategoryContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.category.CategoryContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor() : MVI<UiState, UiEffect, UiAction>(UiState()) {

    override fun onAction(uiAction: UiAction) {
    }
}
