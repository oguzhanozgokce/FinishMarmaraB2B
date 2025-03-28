package com.oguzhanozgokce.finishmarmarab2b.ui.orderlist

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData

class OrderListScreenPreviewProvider : PreviewParameterProvider<OrderListContract.UiState> {
    override val values: Sequence<OrderListContract.UiState>
        get() = sequenceOf(
            OrderListContract.UiState(
                isLoading = false,
                orderInfoList = emptyList()
            ),
            OrderListContract.UiState(
                orderInfoList = PreviewMockData.defaultOrderInfoList,
                isLoading = false,
            ),
        )
}
