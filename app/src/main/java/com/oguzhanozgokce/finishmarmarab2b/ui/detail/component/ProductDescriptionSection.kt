package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

@Composable
fun ProductDescriptionSection(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(colors.white)
            .padding(padding.dimension16),
        verticalArrangement = Arrangement.spacedBy(padding.dimension4)
    ) {
        Text(
            text = "Product Description",
            style = typography.bodyMediumNormal().copy(
                fontWeight = FontWeight.Medium
            ),
        )
        Spacer(modifier = Modifier.height(padding.dimension4))
        HorizontalDivider(
            thickness = padding.dimension1,
            color = colors.lightGray.copy(alpha = 0.5f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = padding.dimension4)
        )
        Text(
            text = product.description,
            style = typography.bodySmallLight().copy(
                fontWeight = FontWeight.Normal
            ),
        )
        product.seller?.let { seller ->
            Text(
                text = "- This product will be sent by ${seller.name}",
                style = typography.bodySmallLight().copy(
                    fontWeight = FontWeight.Normal
                ),
            )
        }
        Text(
            text = "- The sales price of the product you have reviewed is determined by the seller.",
            style = typography.bodySmallLight().copy(
                fontWeight = FontWeight.Normal
            ),
        )
        Text(
            text = "- A maximum of 15 pieces of this product can be ordered. Marmara reserves the right to cancel orders over 15 pieces.",
            style = typography.bodySmallLight().copy(
                fontWeight = FontWeight.Normal
            ),
        )
    }
}
