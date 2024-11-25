package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.oguzhanozgokce.finishmarmarab2b.ui.home.sampleProductList

class FavoriteScreenPreviewProvider : PreviewParameterProvider<FavoriteContract.UiState> {
    override val values: Sequence<FavoriteContract.UiState>
        get() = sequenceOf(
            FavoriteContract.UiState(
                isLoading = true,
                favoriteList = emptyList(),
            ),
            FavoriteContract.UiState(
                isLoading = false,
                favoriteList = emptyList(),
            ),
            FavoriteContract.UiState(
                isLoading = false,
                favoriteList = sampleProductList,
            ),
        )
}
