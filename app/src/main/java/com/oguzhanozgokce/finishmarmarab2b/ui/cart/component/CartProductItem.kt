package com.oguzhanozgokce.finishmarmarab2b.ui.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun CartProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onDeleteBasket: () -> Unit,
    onAddToBasket: () -> Unit,
    onDetail: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.cardBackground)
            .height(210.dp)
            .padding(vertical = padding.dimension8),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(padding.dimension8)
    ) {
        product.seller.let {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = padding.dimension8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Seller: ",
                    style = typography.bodySmallNormal().copy(
                        color = colors.text.copy(alpha = 0.6f)
                    ),
                )
                Text(
                    text = it.name,
                    style = typography.titleMediumMedium(),
                )
            }
            FMHorizontalDivider()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.dimension8),
            horizontalArrangement = Arrangement.spacedBy(padding.dimension4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(padding.dimension16),
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = "Shopping Cart",
                tint = colors.primary
            )
            Text(
                text = "Forecast, Tuesday, February 4 at the door",
                style = typography.bodySmallNormal(),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.dimension8)
                .noRippleClickable { onDetail() },
            horizontalArrangement = Arrangement.spacedBy(padding.dimension4),
        ) {
            ProductCounter(
                product = product,
                onIncrease = { onAddToBasket() },
                onDelete = { onDeleteBasket() }
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(padding.dimension4)
                ) {
                    Text(
                        text = product.title,
                        style = typography.titleMediumMedium(),
                    )
                    Text(
                        text = product.description,
                        style = typography.bodySmallNormal().copy(
                            color = colors.text.copy(alpha = 0.6f)
                        ),
                    )
                    if (product.stock in 1..10) {
                        Text(
                            text = if (product.stock == 1) "Last 1 Product!" else "Stock ${product.stock} products left",
                            style = typography.bodySmallNormal(),
                            color = if (product.stock == 1) colors.error else colors.text,
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = padding.dimension8, bottom = padding.dimension12),
                    text = "$${product.discountedPrice}",
                    style = typography.titleMediumMedium(),
                    color = colors.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardProductItem() {
    FMTheme {
        CartProductItem(
            product = PreviewMockData.defaultProduct,
            onDeleteBasket = {},
            onAddToBasket = {},
            onDetail = {}
        )
    }
}
