package com.oguzhanozgokce.finishmarmarab2b.ui.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

private const val ON_BACKGROUND_ALPHA = 0.1F

@Composable
fun CartProductItemShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.cardBackground)
            .height(210.dp)
            .padding(vertical = padding.dimension8)
            .shimmer(true),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(padding.dimension8)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding.dimension8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(padding.dimension60)
                    .height(padding.dimension12)
                    .clip(RoundedCornerShape(padding.dimension4))
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
            )
            Spacer(modifier = Modifier.width(padding.dimension4))
            Box(
                modifier = Modifier
                    .width(padding.dimension100)
                    .height(padding.dimension16)
                    .clip(RoundedCornerShape(padding.dimension4))
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
            )
        }
        FMHorizontalDivider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.dimension8),
            horizontalArrangement = Arrangement.spacedBy(padding.dimension4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(padding.dimension20)
                    .clip(CircleShape)
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
            )
            Box(
                modifier = Modifier
                    .width(padding.dimension180)
                    .height(padding.dimension16)
                    .clip(RoundedCornerShape(padding.dimension4))
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
            )
        }
        Spacer(modifier = Modifier.height(padding.dimension6))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.dimension8),
            horizontalArrangement = Arrangement.spacedBy(padding.dimension4),
        ) {
            Box(
                modifier = Modifier
                    .size(padding.dimension80)
                    .clip(RoundedCornerShape(padding.dimension4))
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
            )

            Column(
                modifier = Modifier
                    .padding(bottom = padding.dimension8)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(padding.dimension4)
            ) {
                Box(
                    modifier = Modifier
                        .width(150.dp)
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
                Spacer(modifier = Modifier.height(padding.dimension6))
                Box(
                    modifier = Modifier
                        .width(padding.dimension120)
                        .height(padding.dimension12)
                        .clip(RoundedCornerShape(padding.dimension4))
                        .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .width(padding.dimension80)
                        .height(padding.dimension20)
                        .align(Alignment.End)
                        .clip(RoundedCornerShape(padding.dimension4))
                        .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun CartProductItemShimmerPreview() {
    FMTheme {
        CartProductItemShimmer()
    }
}
