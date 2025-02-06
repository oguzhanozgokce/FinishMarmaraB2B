package com.oguzhanozgokce.finishmarmarab2b.ui.cart

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class CartScreenPreviewProvider : PreviewParameterProvider<CartContract.UiState> {
    override val values: Sequence<CartContract.UiState>
        get() = sequenceOf(
            CartContract.UiState(
                isLoading = true,
            ),
            CartContract.UiState(
                isLoading = false,
            ),
        )
}
