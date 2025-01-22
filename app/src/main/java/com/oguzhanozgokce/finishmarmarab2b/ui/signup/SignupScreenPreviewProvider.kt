package com.oguzhanozgokce.finishmarmarab2b.ui.signup

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class SignupScreenPreviewProvider : PreviewParameterProvider<SignupContract.UiState> {
    override val values: Sequence<SignupContract.UiState>
        get() = sequenceOf(
            SignupContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            SignupContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            SignupContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}
