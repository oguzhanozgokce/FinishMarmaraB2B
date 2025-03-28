package com.oguzhanozgokce.finishmarmarab2b.ui.orderlist

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.OrderInfo

object OrderListContract {
    data class UiState(
        val isLoading: Boolean = false,
        val orderInfoList: List<OrderInfo> = emptyList(),
        val error: String = "",
    )

    sealed class UiAction

    sealed class UiEffect
}
