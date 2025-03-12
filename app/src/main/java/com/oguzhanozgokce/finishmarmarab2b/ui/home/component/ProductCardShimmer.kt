package com.oguzhanozgokce.finishmarmarab2b.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

private const val ON_BACKGROUND_ALPHA = 0.1F
private const val MAX_WEIGHT_06 = 0.6f
private const val PRODUCT_SHIMMER_COUNT = 6

@Composable
fun ProductCardShimmer(
    modifier: Modifier = Modifier,
    isLoading: Boolean = true
) {
    Card(
        modifier = modifier
            .width(padding.dimension200)
            .height(padding.dimension280)
            .padding(padding.dimension4)
            .shimmer(isLoading),
        shape = RoundedCornerShape(padding.dimension16),
        colors = CardDefaults.cardColors(
            containerColor = colors.cardBackground
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(padding.dimension140)
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA)),
                contentAlignment = Alignment.TopEnd
            ) {
                Box(
                    modifier = Modifier
                        .size(padding.dimension36)
                        .padding(padding.dimension4)
                        .background(
                            color = colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA),
                            shape = CircleShape
                        )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding.dimension8)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(padding.dimension20)
                        .clip(RoundedCornerShape(padding.dimension4))
                        .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                )
                Spacer(modifier = Modifier.height(padding.dimension8))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(MAX_WEIGHT_06)
                        .height(padding.dimension16)
                        .clip(RoundedCornerShape(padding.dimension4))
                        .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                )
                Spacer(modifier = Modifier.height(padding.dimension8))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(padding.dimension60)
                            .height(padding.dimension8)
                            .clip(RoundedCornerShape(padding.dimension4))
                            .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    )
                    Spacer(modifier = Modifier.width(padding.dimension8))
                    Box(
                        modifier = Modifier
                            .width(padding.dimension24)
                            .height(padding.dimension8)
                            .clip(RoundedCornerShape(padding.dimension4))
                            .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    )
                }

                Spacer(modifier = Modifier.height(padding.dimension8))

                Box(
                    modifier = Modifier
                        .width(padding.dimension60)
                        .height(padding.dimension12)
                        .clip(RoundedCornerShape(padding.dimension4))
                        .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                )
                Spacer(modifier = Modifier.height(padding.dimension8))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(padding.dimension40)
                            .height(padding.dimension12)
                            .clip(RoundedCornerShape(padding.dimension4))
                            .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    )
                    Spacer(modifier = Modifier.width(padding.dimension8))
                    Box(
                        modifier = Modifier
                            .width(padding.dimension60)
                            .height(padding.dimension16)
                            .clip(RoundedCornerShape(padding.dimension4))
                            .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    )
                }
            }
        }
    }
}

@Composable
fun ProductListShimmer(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding.dimension8)
    ) {
        repeat(PRODUCT_SHIMMER_COUNT) {
            ProductCardShimmer(
                modifier = Modifier.padding(end = padding.dimension8)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewProductCardShimmer() {
    FMTheme {
        ProductListShimmer()
    }
}
