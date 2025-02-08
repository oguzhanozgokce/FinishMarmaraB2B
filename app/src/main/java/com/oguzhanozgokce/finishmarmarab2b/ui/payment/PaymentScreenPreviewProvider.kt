package com.oguzhanozgokce.finishmarmarab2b.ui.payment

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData

class PaymentScreenPreviewProvider : PreviewParameterProvider<PaymentContract.UiState> {
    override val values: Sequence<PaymentContract.UiState>
        get() = sequenceOf(
            PaymentContract.UiState(
                isLoading = true,
                products = emptyList(),
            ),
            PaymentContract.UiState(
                isLoading = false,
                products = emptyList(),
            ),
            PaymentContract.UiState(
                isLoading = false,
                products = PreviewMockData.defaultProductList,
            ),
        )
}
