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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

private const val ON_BACKGROUND_ALPHA = 0.1F
private const val WEIGHT_1F = 1f

@Composable
fun EvaluationItemShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.cardBackground)
            .padding(vertical = FMTheme.padding.dimension8)
            .shimmer(true)
    ) {
        Column(
            modifier = Modifier
                .background(colors.cardBackground)
                .padding(horizontal = FMTheme.padding.dimension8)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.cardBackground),
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

                Spacer(modifier = Modifier.weight(WEIGHT_1F))

                Box(
                    modifier = Modifier
                        .width(FMTheme.padding.dimension80)
                        .height(FMTheme.padding.dimension12)
                        .clip(RoundedCornerShape(FMTheme.padding.dimension4))
                        .background(colors.onBackground.copy(ON_BACKGROUND_ALPHA))
                        .shimmer(true)
                )
            }

            Spacer(modifier = Modifier.height(FMTheme.padding.dimension8))

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(FMTheme.padding.dimension32)
                    .clip(RoundedCornerShape(FMTheme.padding.dimension4))
                    .background(colors.onBackground.copy(ON_BACKGROUND_ALPHA))
                    .shimmer(true)
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
                    Box(
                        modifier = Modifier
                            .size(FMTheme.padding.dimension32)
                            .clip(CircleShape)
                            .background(colors.onBackground.copy(ON_BACKGROUND_ALPHA))
                            .shimmer(true)
                    )

                    Spacer(modifier = Modifier.width(FMTheme.padding.dimension8))

                    Box(
                        modifier = Modifier
                            .width(FMTheme.padding.dimension120)
                            .height(FMTheme.padding.dimension16)
                            .clip(RoundedCornerShape(FMTheme.padding.dimension4))
                            .background(colors.onBackground.copy(ON_BACKGROUND_ALPHA))
                            .shimmer(true)
                    )

                    Spacer(modifier = Modifier.weight(WEIGHT_1F))

                    Box(
                        modifier = Modifier
                            .width(FMTheme.padding.dimension80)
                            .height(FMTheme.padding.dimension12)
                            .clip(RoundedCornerShape(FMTheme.padding.dimension4))
                            .background(colors.onBackground.copy(ON_BACKGROUND_ALPHA))
                            .shimmer(true)
                    )
                }

                Spacer(modifier = Modifier.height(FMTheme.padding.dimension8))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(FMTheme.padding.dimension32)
                        .clip(RoundedCornerShape(FMTheme.padding.dimension4))
                        .background(colors.onBackground.copy(ON_BACKGROUND_ALPHA))
                        .shimmer(true)
                )
                Spacer(modifier = Modifier.height(FMTheme.padding.dimension4))
            }
        }
    }
    Spacer(modifier = Modifier.height(FMTheme.padding.dimension8))
}

@PreviewLightDark
@Composable
fun EvaluationItemShimmerPreview() {
    FMTheme {
        EvaluationItemShimmer()
    }
}
