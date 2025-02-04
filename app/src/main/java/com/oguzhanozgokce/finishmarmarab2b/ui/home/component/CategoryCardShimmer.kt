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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun CategoryCardShimmer(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .wrapContentSize(),
        shape = RoundedCornerShape(padding.dimension16),
        colors = CardDefaults.cardColors(
            containerColor = colors.white
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
                    .shimmer(isLoading = true, shape = CircleShape)
                    .background(color = colors.background, shape = CircleShape),
            )

            Spacer(modifier = Modifier.width(padding.dimension12))

            Box(
                modifier = Modifier
                    .height(padding.dimension16)
                    .fillMaxWidth(0.5f)
                    .shimmer(isLoading = true, shape = RoundedCornerShape(4.dp))
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(4.dp)
                    )
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

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun CategoryCardShimmerPreview() {
    FMTheme {
        CategoryListShimmer()
    }
}
