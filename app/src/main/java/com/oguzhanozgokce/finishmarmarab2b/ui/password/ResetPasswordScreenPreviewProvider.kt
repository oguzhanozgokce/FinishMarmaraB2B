package com.oguzhanozgokce.finishmarmarab2b.ui.password

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ResetPasswordScreenPreviewProvider : PreviewParameterProvider<ResetPasswordContract.UiState> {
    override val values: Sequence<ResetPasswordContract.UiState>
        get() = sequenceOf(
            ResetPasswordContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            ResetPasswordContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            ResetPasswordContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}
