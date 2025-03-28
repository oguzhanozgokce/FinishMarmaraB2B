package com.oguzhanozgokce.finishmarmarab2b.ui.orderlist

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.order.GetOrderInfoUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.OrderListContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.OrderListContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.OrderListContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val getOrderInfoUseCase: GetOrderInfoUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        getOrderInfo()
    }

    override fun onAction(uiAction: UiAction) {
    }

    private fun getOrderInfo() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getOrderInfoUseCase().fold(
                onSuccess = {
                    updateState { copy(isLoading = false, orderInfoList = it) }
                },
                onError = { error ->
                    updateState { copy(isLoading = false, error = error) }
                }
            )
        }
    }
}
