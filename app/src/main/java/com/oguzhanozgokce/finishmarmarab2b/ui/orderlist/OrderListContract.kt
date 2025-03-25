package com.oguzhanozgokce.finishmarmarab2b.ui.orderlist

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.OrderInfo

object OrderListContract {
    data class UiState(
        val isLoading: Boolean = false,
        val orderList: List<OrderInfo> = emptyList(),
    )

    sealed class UiAction

    sealed class UiEffect
}
