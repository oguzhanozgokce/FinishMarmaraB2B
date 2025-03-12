package com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

@Composable
fun EvaluationItem(
    modifier: Modifier = Modifier,
    questionAnswer: QuestionAnswer,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.cardBackground)
            .padding(vertical = FMTheme.padding.dimension8)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = FMTheme.padding.dimension8)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(colors.cardBackground)
                        .border(
                            width = FMTheme.padding.dimension1,
                            color = colors.primary,
                            shape = RoundedCornerShape(FMTheme.padding.dimension8)
                        )
                        .padding(FMTheme.padding.dimension8)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = "Question",
                        style = FMTheme.typography.titleMediumMedium()
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = FMTheme.padding.dimension8),
                    text = questionAnswer.date,
                    style = FMTheme.typography.bodySmallNormal().copy(
                        color = colors.text.copy(alpha = 0.6f)
                    )
                )
            }
            Spacer(modifier = Modifier.height(FMTheme.padding.dimension8))
            Text(
                modifier = Modifier
                    .padding(horizontal = FMTheme.padding.dimension8),
                text = questionAnswer.question,
                style = FMTheme.typography.bodyMediumNormal().copy(
                    color = colors.text.copy(alpha = 0.6f)
                ),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(FMTheme.padding.dimension8))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        colors.gray,
                        shape = RoundedCornerShape(FMTheme.padding.dimension8)
                    )
                    .padding(FMTheme.padding.dimension8)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(FMTheme.padding.dimension32)
                            .clip(CircleShape),
                        model = questionAnswer.sellerImageUrl,
                        contentDescription = stringResource(R.string.seller_image)
                    )
                    Spacer(modifier = Modifier.width(FMTheme.padding.dimension8))
                    Text(
                        text = questionAnswer.sellerName,
                        style = FMTheme.typography.titleMediumMedium()
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        text = questionAnswer.date,
                        style = FMTheme.typography.bodySmallNormal().copy(
                            color = colors.text.copy(alpha = 0.6f)
                        )
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(top = FMTheme.padding.dimension8),
                    text = questionAnswer.answer,
                    style = FMTheme.typography.bodyMediumNormal().copy(
                        color = colors.text.copy(alpha = 0.8f)
                    ),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(FMTheme.padding.dimension8))
}

@PreviewLightDark
@Composable
fun EvaluationItemPreview() {
    FMTheme {
        EvaluationItem(
            questionAnswer = PreviewMockData.defaultQuestionAnswer
        )
    }
}
