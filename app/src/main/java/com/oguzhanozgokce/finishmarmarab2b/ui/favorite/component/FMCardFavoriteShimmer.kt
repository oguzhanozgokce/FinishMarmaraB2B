package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

private const val ON_BACKGROUND_ALPHA = 0.1f

@Composable
fun FMCardFavoriteShimmer(
    modifier: Modifier = Modifier,
    isLoading: Boolean = true
) {
    FMCard(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = padding.dimension8, vertical = padding.dimension4)
            .shimmer(isLoading),
        cardColors = CardDefaults.cardColors(
            containerColor = colors.cardBackground
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .padding(
                        top = padding.dimension8,
                        bottom = padding.dimension8,
                        start = padding.dimension8
                    )
                    .clip(RoundedCornerShape(padding.dimension8))
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
            )
            Spacer(modifier = Modifier.width(padding.dimension8))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding.dimension8),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .height(padding.dimension16)
                                .clip(RoundedCornerShape(padding.dimension4))
                                .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                        )
                        Spacer(modifier = Modifier.height(padding.dimension6))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(padding.dimension12)
                                .clip(RoundedCornerShape(padding.dimension4))
                                .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(padding.dimension36)
                            .clip(CircleShape)
                            .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    )
                }
                Spacer(modifier = Modifier.height(padding.dimension6))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(padding.dimension4)
                ) {
                    Box(
                        modifier = Modifier
                            .width(padding.dimension24)
                            .height(padding.dimension12)
                            .clip(RoundedCornerShape(padding.dimension4))
                            .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    )
                    Box(
                        modifier = Modifier
                            .width(padding.dimension60)
                            .height(padding.dimension12)
                            .clip(RoundedCornerShape(padding.dimension4))
                            .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    )
                    Box(
                        modifier = Modifier
                            .width(padding.dimension36)
                            .height(padding.dimension12)
                            .clip(RoundedCornerShape(padding.dimension4))
                            .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    )
                }
                Spacer(modifier = Modifier.height(padding.dimension6))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(padding.dimension4)
                ) {
                    Box(
                        modifier = Modifier
                            .size(padding.dimension16)
                            .clip(CircleShape)
                            .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    )
                    Box(
                        modifier = Modifier
                            .width(padding.dimension100)
                            .height(padding.dimension12)
                            .clip(RoundedCornerShape(padding.dimension4))
                            .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    )
                }
                Spacer(modifier = Modifier.height(padding.dimension6))

                Box(
                    modifier = Modifier
                        .width(padding.dimension60)
                        .height(padding.dimension12)
                        .clip(RoundedCornerShape(padding.dimension4))
                        .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                )
                Spacer(modifier = Modifier.height(padding.dimension6))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(padding.dimension4)
                ) {
                    Box(
                        modifier = Modifier
                            .width(padding.dimension40)
                            .height(padding.dimension12)
                            .clip(RoundedCornerShape(padding.dimension4))
                            .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    )
                    Box(
                        modifier = Modifier
                            .width(padding.dimension60)
                            .height(padding.dimension16)
                            .clip(RoundedCornerShape(padding.dimension4))
                            .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(padding.dimension36)
                        .clip(RoundedCornerShape(padding.dimension8))
                        .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                )
            }
        }
    }
}

@Composable
fun FavoriteListShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding.dimension8)
    ) {
        repeat(4) {
            FMCardFavoriteShimmer(
                modifier = Modifier.padding(end = padding.dimension8)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun FMCardFavoriteShimmerPreview() {
    FMTheme {
        FavoriteListShimmer()
    }
}
