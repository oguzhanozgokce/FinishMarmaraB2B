package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

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
                    style = typography.bodySmallLight().copy(color = colors.primary)
                )
            }
        }
        FMHorizontalDivider(paddingValues = PaddingValues(bottom = padding.dimension4))
        QuestionList(questionAnswers = questionAnswers)
    }
}

@Composable
fun ProductQuestionsShimmer(
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
            Box(
                modifier = Modifier
                    .width(180.dp)
                    .height(padding.dimension16)
                    .clip(RoundedCornerShape(padding.dimension4))
                    .background(colors.onBackground.copy(alpha = 0.1f))
                    .shimmer(true)
            )
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(padding.dimension12)
                    .clip(RoundedCornerShape(padding.dimension4))
                    .background(colors.primary.copy(alpha = 0.2f))
                    .shimmer(true)
            )
        }

        FMHorizontalDivider(paddingValues = PaddingValues(bottom = padding.dimension4))
        repeat(2) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = colors.background)
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(padding.dimension12)
                            .clip(RoundedCornerShape(padding.dimension4))
                            .background(colors.onBackground.copy(alpha = 0.1f))
                            .shimmer(true)
                    )
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(padding.dimension12)
                            .clip(RoundedCornerShape(padding.dimension4))
                            .background(colors.onBackground.copy(alpha = 0.1f))
                            .shimmer(true)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(padding.dimension20)
                        .clip(RoundedCornerShape(padding.dimension4))
                        .background(colors.onBackground.copy(alpha = 0.1f))
                        .shimmer(true)
                )

                Spacer(modifier = Modifier.height(padding.dimension8))

                FMCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(vertical = 8.dp),
                    content = {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = padding.dimension4)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(padding.dimension24)
                                        .clip(CircleShape)
                                        .background(colors.onBackground.copy(alpha = 0.1f))
                                        .shimmer(true)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Box(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(padding.dimension12)
                                        .clip(RoundedCornerShape(padding.dimension4))
                                        .background(colors.onBackground.copy(alpha = 0.1f))
                                        .shimmer(true)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(padding.dimension16)
                                    .padding(horizontal = 8.dp, vertical = padding.dimension4)
                                    .clip(RoundedCornerShape(padding.dimension4))
                                    .background(colors.onBackground.copy(alpha = 0.1f))
                                    .shimmer(true)
                            )
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(padding.dimension8))
        }
    }
}
