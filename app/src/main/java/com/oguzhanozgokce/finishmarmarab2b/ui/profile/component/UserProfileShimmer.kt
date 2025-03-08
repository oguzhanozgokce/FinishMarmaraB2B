package com.oguzhanozgokce.finishmarmarab2b.ui.profile.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

private const val ON_BACKGROUND_ALPHA = 0.1f

@Composable
fun UserProfileShimmer(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding.dimension16),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(padding.dimension80)
                .padding(padding.dimension8)
                .clip(CircleShape)
                .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                .shimmer(true)
        )

        Spacer(modifier = Modifier.width(padding.dimension16))

        Column(
            modifier = Modifier
                .padding(padding.dimension8)
        ) {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(padding.dimension20)
                    .clip(RoundedCornerShape(padding.dimension4))
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    .shimmer(true)
            )

            Spacer(modifier = Modifier.height(padding.dimension8))

            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(padding.dimension20)
                    .clip(RoundedCornerShape(padding.dimension4))
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    .shimmer(true)
            )

            Spacer(modifier = Modifier.height(padding.dimension8))

            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(padding.dimension16)
                    .clip(RoundedCornerShape(padding.dimension4))
                    .background(colors.onBackground.copy(alpha = ON_BACKGROUND_ALPHA))
                    .shimmer(true)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun UserProfileShimmerPreview() {
    FMTheme {
        UserProfileShimmer()
    }
}
