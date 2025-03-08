package com.oguzhanozgokce.finishmarmarab2b.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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

private const val ON_BACKGROUND_ALPHA = 0.1f

@Composable
fun CategoryCardShimmer(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .shimmer(true),
        shape = RoundedCornerShape(padding.dimension16),
        colors = CardDefaults.cardColors(
            containerColor = colors.cardBackground
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = padding.dimension16,
                vertical = padding.dimension8
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(padding.dimension36)
                    .background(color = colors.background, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(padding.dimension36)
                        .clip(CircleShape)
                        .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                )
            }

            Spacer(modifier = Modifier.width(padding.dimension12))

            Box(
                modifier = Modifier
                    .width(padding.dimension100)
                    .height(padding.dimension16)
                    .clip(RoundedCornerShape(padding.dimension4))
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
            )
        }
    }
}

@Composable
fun CategoryListShimmer(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shimmer(isLoading = true)
    ) {
        repeat(5) {
            CategoryCardShimmer(
                modifier = Modifier.padding(end = padding.dimension8)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CategoryCardShimmerPreview() {
    FMTheme {
        CategoryListShimmer()
    }
}
