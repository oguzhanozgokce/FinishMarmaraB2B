package com.oguzhanozgokce.finishmarmarab2b.ui.category

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData

class CategoryScreenPreviewProvider : PreviewParameterProvider<CategoryContract.UiState> {
    override val values: Sequence<CategoryContract.UiState>
        get() = sequenceOf(
            CategoryContract.UiState(
                isLoading = true,
                categoryProducts = emptyList(),
            ),
            CategoryContract.UiState(
                isLoading = false,
                categoryProducts = emptyList(),
            ),
            CategoryContract.UiState(
                isLoading = false,
                categoryProducts = PreviewMockData.defaultProductList,
            ),
        )
}
