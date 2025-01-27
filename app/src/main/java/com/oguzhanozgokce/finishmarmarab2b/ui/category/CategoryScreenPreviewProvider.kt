package com.oguzhanozgokce.finishmarmarab2b.ui.category

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class CategoryScreenPreviewProvider : PreviewParameterProvider<CategoryContract.UiState> {
    override val values: Sequence<CategoryContract.UiState>
        get() = sequenceOf(
            CategoryContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            CategoryContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            CategoryContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}
