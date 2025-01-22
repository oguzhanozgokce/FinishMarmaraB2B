package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMVerticalDivider
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.RatingBar
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

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
                style = typography.headMediumSemiBold().copy(
                    color = colors.primary
                ),
            )
        }
        Spacer(modifier = Modifier.width(padding.dimension8))
        Text(
            text = product.title,
            style = typography.titleMediumMedium()
        )
    }
    Spacer(modifier = Modifier.height(padding.dimension8))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${product.rate}",
            style = typography.bodySmallLight()
        )
        RatingBar(
            rating = product.rate,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(padding.dimension4))
        FMVerticalDivider(
            height = padding.dimension16,
            paddingValues = PaddingValues(padding.dimension4)
        )
        Text(
            text = "${product.commentCount} Evaluation",
            style = typography.bodySmallLight()
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
            style = typography.bodySmallNormal(),
        )
    }
    FMHorizontalDivider(
        paddingValues = PaddingValues(vertical = padding.dimension8)
    )
}
