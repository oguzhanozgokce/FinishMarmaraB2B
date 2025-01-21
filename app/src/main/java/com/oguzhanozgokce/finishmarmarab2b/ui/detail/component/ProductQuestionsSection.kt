package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.compose.LazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

@Composable
fun ProductQuestionsSection(
    questionAnswers: LazyPagingItems<QuestionAnswer>?,
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(colors.white)
            .padding(vertical = padding.dimension8, horizontal = padding.dimension16),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Product Questions & Answers",
                style = typography.bodyMediumNormal().copy(
                    fontWeight = FontWeight.Medium
                ),
            )
            TextButton(
                onClick = { onSeeAllClick() }
            ) {
                Text(
                    text = "See All >",
                    fontSize = fontSize.small,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Light,
                    color = colors.primary
                )
            }
        }
        HorizontalDivider(
            thickness = padding.dimension1,
            color = colors.lightGray.copy(alpha = 0.5f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = padding.dimension4)
        )
        QuestionList(
            questionAnswers = questionAnswers
        )
    }
}
