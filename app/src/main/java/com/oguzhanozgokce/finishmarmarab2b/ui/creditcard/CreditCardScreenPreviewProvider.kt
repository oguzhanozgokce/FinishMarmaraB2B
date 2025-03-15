package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class CreditCardScreenPreviewProvider : PreviewParameterProvider<CreditCardContract.UiState> {
    override val values: Sequence<CreditCardContract.UiState>
        get() = sequenceOf(
            CreditCardContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            CreditCardContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            CreditCardContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}
