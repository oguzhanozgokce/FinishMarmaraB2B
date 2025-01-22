package com.oguzhanozgokce.finishmarmarab2b.ui.detail

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData

class DetailScreenPreviewProvider : PreviewParameterProvider<DetailContract.UiState> {

    override val values: Sequence<DetailContract.UiState>
        get() = sequenceOf(
            DetailContract.UiState(
                isLoading = true,
                product = null
            ),
            DetailContract.UiState(
                isLoading = false,
                product = PreviewMockData.defaultProduct
            ),
            DetailContract.UiState(
                isLoading = false,
                product = PreviewMockData.productWithNoImage
            ),
            DetailContract.UiState(
                isLoading = false,
                product = PreviewMockData.outOfStockProduct
            )
        )
}
