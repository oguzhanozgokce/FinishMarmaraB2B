package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class FavoriteScreenPreviewProvider : PreviewParameterProvider<FavoriteContract.UiState> {
    override val values: Sequence<FavoriteContract.UiState>
        get() = sequenceOf(
            FavoriteContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            FavoriteContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            FavoriteContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}