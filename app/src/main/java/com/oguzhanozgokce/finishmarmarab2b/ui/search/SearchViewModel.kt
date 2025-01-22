package com.oguzhanozgokce.finishmarmarab2b.ui.search

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : MVI<UiState, UiEffect, UiAction>(UiState()) {

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.OnSearchValueChange -> {
                updateState { copy(searchValue = uiAction.value) }
            }
        }
    }
}
