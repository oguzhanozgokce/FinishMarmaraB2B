package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.RatingBar
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

@Composable
fun ProductDetailHeader(
    product: Product,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        product.seller?.let { seller ->
            Text(
                text = seller.name,
                fontSize = fontSize.medium,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = colors.primary
            )
        }
        Spacer(modifier = Modifier.width(padding.dimension8))
        Text(
            text = product.title,
            fontSize = fontSize.medium,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            color = colors.black
        )
    }
    Spacer(modifier = Modifier.height(padding.dimension8))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${product.rate}",
            fontSize = fontSize.small,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Light,
            color = colors.black
        )
        RatingBar(
            rating = product.rate,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(padding.dimension4))
        VerticalDivider(
            thickness = padding.dimension1,
            color = colors.lightGray,
            modifier = Modifier
                .height(padding.dimension16)
                .padding(padding.dimension4)
        )
        Text(
            text = "${product.commentCount} Evaluation",
            fontSize = fontSize.small,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Light,
            color = colors.black
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = FMTheme.icons.favorite,
            contentDescription = null,
            tint = colors.red,
            modifier = Modifier.size(padding.dimension16)
        )
        Spacer(modifier = Modifier.width(padding.dimension4))
        Text(
            text = "104 People Added to Favourites",
            fontSize = fontSize.small,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            color = colors.black
        )
    }
    HorizontalDivider(
        thickness = padding.dimension1,
        color = colors.lightGray.copy(alpha = 0.5f),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = padding.dimension8)
    )
}
