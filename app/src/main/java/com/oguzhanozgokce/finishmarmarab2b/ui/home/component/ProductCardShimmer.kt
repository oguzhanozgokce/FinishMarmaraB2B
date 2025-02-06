package com.oguzhanozgokce.finishmarmarab2b.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun ProductCardShimmer(
    modifier: Modifier = Modifier
) {
    FMCard(
        modifier = modifier
            .width(padding.dimension200)
            .height(padding.dimension280)
            .padding(
                start = padding.dimension4,
                end = padding.dimension4,
                bottom = padding.dimension8
            ),
        shape = RoundedCornerShape(padding.dimension16),
        cardColors = CardDefaults.cardColors(
            containerColor = colors.cardBackground
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(padding.dimension140)
                .background(colors.cardBackground),
            contentAlignment = Alignment.TopEnd
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmer(
                        isLoading = true,
                        shape = RoundedCornerShape(
                            topStart = padding.dimension16,
                            topEnd = padding.dimension16
                        )
                    )
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
                    ) // **Daha belirgin hale getirildi**
            )

            Box(
                modifier = Modifier
                    .size(padding.dimension36)
                    .shimmer(isLoading = true, shape = CircleShape)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                    .align(Alignment.TopEnd)
                    .offset(x = (-padding.dimension8), y = padding.dimension4)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding.dimension8),
            verticalArrangement = Arrangement.spacedBy(padding.dimension8)
        ) {
            repeat(5) {
                Box(
                    modifier = Modifier
                        .height(padding.dimension16)
                        .fillMaxWidth(0.8f - (it * 0.1f))
                        .shimmer(isLoading = true, shape = RoundedCornerShape(4.dp))
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(4.dp)
                        )
                )
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
        repeat(5) {
            ProductCardShimmer(
                modifier = Modifier.padding(end = padding.dimension8)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun PreviewProductCardShimmer() {
    FMTheme {
        ProductListShimmer()
    }
}
