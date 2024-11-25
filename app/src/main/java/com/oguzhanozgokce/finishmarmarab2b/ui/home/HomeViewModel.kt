package com.oguzhanozgokce.finishmarmarab2b.ui.home

import com.oguzhanozgokce.finishmarmarab2b.core.presentation.delegation.UiHandler
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : UiHandler<UiState, UiEffect>(UiState()) {

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.FetchCategory -> fetchCategory()
            is UiAction.FetchProduct -> fetchProduct()
            is UiAction.ToggleFavorite -> toggleFavorite()
        }
    }

    private fun fetchCategory() {

    }

    private fun fetchProduct() {

    }

    private fun toggleFavorite() {

    }
}