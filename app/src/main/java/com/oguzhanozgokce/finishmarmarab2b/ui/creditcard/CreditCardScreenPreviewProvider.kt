package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData

class CreditCardScreenPreviewProvider : PreviewParameterProvider<CreditCardContract.UiState> {
    override val values: Sequence<CreditCardContract.UiState>
        get() = sequenceOf(
            CreditCardContract.UiState(
                isLoading = true,
                creditCardList = emptyList(),
            ),
            CreditCardContract.UiState(
                isLoading = false,
                creditCardList = emptyList(),
            ),
            CreditCardContract.UiState(
                isLoading = false,
                creditCardList = PreviewMockData.defaultCart
            ),
        )
}
