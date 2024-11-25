package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import com.oguzhanozgokce.finishmarmarab2b.core.presentation.delegation.UiHandler
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor() : UiHandler<UiState, UiEffect>(UiState()) {

    fun onAction(uiAction: UiAction) {
    }

}