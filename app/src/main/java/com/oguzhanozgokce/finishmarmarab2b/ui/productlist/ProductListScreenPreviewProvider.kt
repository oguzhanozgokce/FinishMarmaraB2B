package com.oguzhanozgokce.finishmarmarab2b.ui.productlist

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ProductListScreenPreviewProvider : PreviewParameterProvider<ProductListContract.UiState> {
    override val values: Sequence<ProductListContract.UiState>
        get() = sequenceOf(
            ProductListContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            ProductListContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            ProductListContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}