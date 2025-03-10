package com.oguzhanozgokce.finishmarmarab2b.ui.evaluation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class EvaluationScreenPreviewProvider : PreviewParameterProvider<EvaluationContract.UiState> {
    override val values: Sequence<EvaluationContract.UiState>
        get() = sequenceOf(
            EvaluationContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            EvaluationContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            EvaluationContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}