package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

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
        FMHorizontalDivider()
        Text(
            text = product.description,
            style = typography.bodySmallLight().copy(
                fontWeight = FontWeight.Normal
            ),
        )
        product.seller?.let { seller ->
            Text(
                text = stringResource(R.string.product_shipping_seller, seller.name),
                style = typography.bodySmallLight().copy(
                    fontWeight = FontWeight.Normal
                ),
            )
        }
        Text(
            text = stringResource(R.string.product_price_determined_by_seller),
            style = typography.bodySmallLight().copy(
                fontWeight = FontWeight.Normal
            ),
        )
        Text(
            text = stringResource(R.string.product_max_order_info),
            style = typography.bodySmallLight().copy(
                fontWeight = FontWeight.Normal
            ),
        )
    }
}
