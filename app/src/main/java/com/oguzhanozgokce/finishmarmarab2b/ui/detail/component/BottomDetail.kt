package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlinedButton
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun BottomDetail(
    product: Product,
    onAddToCart: (Int) -> Unit,
    onNowAddToCart: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.white)
            .padding(end = padding.dimension8),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(padding.dimension16),
            verticalArrangement = Arrangement.spacedBy(padding.dimension8)
        ) {
            Text(
                text = "${product.price} TL",
                style = typography.titleSmallMedium(),
                textDecoration = TextDecoration.LineThrough
            )
            Text(
                text = "${product.discountedPrice} TL",
                style = typography.titleMediumSemiBold(),
            )
        }
        FMButton(
            text = "Basket",
            onClick = { onAddToCart(product.id) },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            height = padding.dimension48
        )
        Spacer(modifier = Modifier.width(padding.dimension8))
        FMOutlinedButton(
            text = "Buy Now",
            onClick = onNowAddToCart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            height = padding.dimension48
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomDetailPreview() {
    FMTheme {
        BottomDetail(
            product = PreviewMockData.defaultProduct,
            onAddToCart = {},
            onNowAddToCart = {},
        )
    }
}
