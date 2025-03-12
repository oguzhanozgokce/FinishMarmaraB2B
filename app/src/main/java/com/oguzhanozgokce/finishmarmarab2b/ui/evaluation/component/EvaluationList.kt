package com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

private const val SHIMMER_COUNT = 4

@Composable
fun EvaluationList(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    questionAnswers: List<QuestionAnswer>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        if (isLoading) {
            items(SHIMMER_COUNT) {
                EvaluationItemShimmer()
            }
        } else {
            items(questionAnswers.size) { index ->
                EvaluationItem(
                    questionAnswer = questionAnswers[index]
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun EvaluationListPreview() {
    FMTheme {
        EvaluationList(
            isLoading = false,
            questionAnswers = PreviewMockData.sampleQuestionAnswersData
        )
    }
}
