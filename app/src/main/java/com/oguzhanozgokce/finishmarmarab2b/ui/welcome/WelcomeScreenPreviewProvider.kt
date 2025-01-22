package com.oguzhanozgokce.finishmarmarab2b.ui.welcome

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class WelcomeScreenPreviewProvider : PreviewParameterProvider<WelcomeContract.UiState> {
    override val values: Sequence<WelcomeContract.UiState>
        get() = sequenceOf(
            WelcomeContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            WelcomeContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            WelcomeContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}
