package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

private const val ON_BACKGROUND_ALPHA = 0.1f
private const val MAX_WIDTH_1 = 1f
private const val MAX_WIDTH_05 = 0.5f
private const val MAX_WIDTH_08 = 0.8f
private const val MAX_WIDTH_03 = 0.3f
private const val MAX_WIDTH_04 = 0.4f

@Composable
fun ProductDetailShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(padding.dimension16)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(padding.dimension8))
                .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                .shimmer(true)
        )

        Spacer(modifier = Modifier.height(padding.dimension16))

        Box(
            modifier = Modifier
                .fillMaxWidth(MAX_WIDTH_05)
                .height(padding.dimension20)
                .clip(RoundedCornerShape(padding.dimension4))
                .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                .shimmer(true)
        )

        Spacer(modifier = Modifier.height(padding.dimension4))

        Box(
            modifier = Modifier
                .fillMaxWidth(MAX_WIDTH_08)
                .height(padding.dimension16)
                .clip(RoundedCornerShape(padding.dimension4))
                .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                .shimmer(true)
        )
        Spacer(modifier = Modifier.height(padding.dimension4))

        Box(
            modifier = Modifier
                .fillMaxWidth(MAX_WIDTH_08)
                .height(padding.dimension16)
                .clip(RoundedCornerShape(padding.dimension4))
                .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                .shimmer(true)
        )

        Spacer(modifier = Modifier.height(padding.dimension4))

        FMHorizontalDivider(
            modifier = Modifier.padding(vertical = padding.dimension16)
        )

        Spacer(modifier = Modifier.height(padding.dimension8))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(padding.dimension8)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(MAX_WIDTH_04)
                    .height(padding.dimension16)
                    .clip(RoundedCornerShape(padding.dimension4))
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    .shimmer(true)
            )
            Spacer(modifier = Modifier.weight(MAX_WIDTH_1))
            Box(
                modifier = Modifier
                    .fillMaxWidth(MAX_WIDTH_03)
                    .height(padding.dimension16)
                    .clip(RoundedCornerShape(padding.dimension4))
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    .shimmer(true)
            )
        }

        Spacer(modifier = Modifier.height(padding.dimension8))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(padding.dimension8)
        ) {
            Box(
                modifier = Modifier
                    .size(padding.dimension48)
                    .clip(CircleShape)
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    .shimmer(true)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(padding.dimension4)
            ) {
                Box(
                    modifier = Modifier
                        .width(padding.dimension120)
                        .height(padding.dimension16)
                        .clip(RoundedCornerShape(padding.dimension4))
                        .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                        .shimmer(true)
                )
                Box(
                    modifier = Modifier
                        .width(padding.dimension80)
                        .height(padding.dimension12)
                        .clip(RoundedCornerShape(padding.dimension4))
                        .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                        .shimmer(true)
                )
            }
        }
        Spacer(modifier = Modifier.height(padding.dimension16))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(padding.dimension12)
                .clip(RoundedCornerShape(padding.dimension4))
                .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                .shimmer(true)
        )
        Spacer(modifier = Modifier.height(padding.dimension4))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(padding.dimension12)
                .clip(RoundedCornerShape(padding.dimension4))
                .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                .shimmer(true)
        )
        Spacer(modifier = Modifier.height(padding.dimension4))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(padding.dimension12)
                .clip(RoundedCornerShape(padding.dimension4))
                .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                .shimmer(true)
        )
        Spacer(modifier = Modifier.height(padding.dimension16))
        ProductQuestionsShimmer()
        Spacer(modifier = Modifier.height(padding.dimension16))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ProductDetailShimmerPreview() {
    FMTheme {
        ProductDetailShimmer()
    }
}
