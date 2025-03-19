package com.oguzhanozgokce.finishmarmarab2b.ui.orderlist

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.OrderListContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.OrderListContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.OrderListContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor() : MVI<UiState, UiEffect, UiAction>(UiState()) {

    override fun onAction(uiAction: UiAction) {
    }

}

object OrderListContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
    )

    sealed class UiAction

    sealed class UiEffect
}