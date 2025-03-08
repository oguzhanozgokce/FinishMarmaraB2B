package com.oguzhanozgokce.finishmarmarab2b.ui.products

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData

class ProductsScreenPreviewProvider : PreviewParameterProvider<ProductsContract.UiState> {
    override val values: Sequence<ProductsContract.UiState>
        get() = sequenceOf(
            ProductsContract.UiState(
                isLoading = true,
                categoryProducts = emptyList(),
            ),
            ProductsContract.UiState(
                isLoading = false,
                categoryProducts = emptyList(),
            ),
            ProductsContract.UiState(
                isLoading = false,
                categoryProducts = PreviewMockData.defaultProductList,
            ),
        )
}
