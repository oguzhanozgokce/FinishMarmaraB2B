package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlinedCard
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

@Composable
fun SellerSection(
    sellerName: String?,
    sellerImageUrl: String?,
    followerCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.cardBackground)
            .padding(padding.dimension16),
        horizontalArrangement = Arrangement.spacedBy(padding.dimension8)
    ) {
        AsyncImage(
            model = sellerImageUrl,
            contentDescription = "Seller Image",
            modifier = Modifier
                .size(padding.dimension48)
                .clip(CircleShape)
                .border(
                    width = padding.dimension1,
                    color = colors.lightGray,
                    shape = CircleShape
                )
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(padding.dimension4)
        ) {
            sellerName?.let {
                Text(
                    text = it,
                    fontSize = fontSize.mediumSmall,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = colors.text
                )
            }
            Text(
                text = "$followerCount Follower",
                style = typography.bodySmallLight(),
            )
        }
        FMOutlinedCard(
            modifier = Modifier
                .padding(padding.dimension8)
                .align(Alignment.CenterVertically),
            border = BorderStroke(
                width = padding.dimension1,
                color = Color.Red
            ),
            shape = RoundedCornerShape(padding.dimension4),
        ) {
            Text(
                modifier = Modifier.padding(padding.dimension8),
                text = "Follow",
                style = typography.headSmallMedium().copy(color = Color.Red)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun SellerSectionPreview() {
    FMTheme {
        SellerSection(
            sellerName = "Apple",
            sellerImageUrl = "",
            followerCount = 15600,
        )
    }
}
