package com.oguzhanozgokce.finishmarmarab2b.ui.payment

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class PaymentScreenPreviewProvider : PreviewParameterProvider<PaymentContract.UiState> {
    override val values: Sequence<PaymentContract.UiState>
        get() = sequenceOf(
            PaymentContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            PaymentContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            PaymentContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}
