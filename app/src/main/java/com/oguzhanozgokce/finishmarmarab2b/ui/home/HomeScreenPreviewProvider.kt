package com.oguzhanozgokce.finishmarmarab2b.ui.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData

class HomeScreenPreviewProvider : PreviewParameterProvider<HomeContract.UiState> {
    override val values: Sequence<HomeContract.UiState>
        get() = sequenceOf(
            HomeContract.UiState(
                isLoading = true,
                categoryList = emptyList(),
            ),
            HomeContract.UiState(
                isLoading = false,
                categoryList = emptyList(),
            ),
            HomeContract.UiState(
                isLoading = false,
                categoryList = PreviewMockData.defaultCategoryList,
            )
        )
}
