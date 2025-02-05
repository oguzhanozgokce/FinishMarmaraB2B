package com.oguzhanozgokce.finishmarmarab2b.ui.payment.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun PaymentProduct(
    modifier: Modifier = Modifier,
    product: Product
) {
    Box(
        modifier = modifier
            .width(padding.dimension120)
            .background(colors.white, shape = RoundedCornerShape(padding.dimension8))
            .border(
                padding.dimension1,
                colors.lightGray.copy(alpha = 0.2f),
                shape = RoundedCornerShape(padding.dimension8)
            )
            .padding(padding.dimension8)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-padding.dimension16))
                .background(
                    colors.lightGray.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(padding.dimension8)
                )
                .padding(horizontal = 6.dp, vertical = padding.dimension2)
        ) {
            Text(
                text = "3",
                style = FMTheme.typography.titleSmallMedium(),
                color = colors.black
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = product.primaryImageUrl,
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(padding.dimension100)
                    .clip(RoundedCornerShape(padding.dimension8))
            )

            Spacer(modifier = Modifier.height(padding.dimension4))

            Text(
                text = "${product.price} TL",
                style = FMTheme.typography.bodyMediumNormal().copy(
                    color = colors.primary
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentProductPreview() {
    FMTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.background)
                .padding(padding.dimension12),
            horizontalArrangement = Arrangement.spacedBy(padding.dimension8)
        ) {
            PaymentProduct(product = PreviewMockData.defaultProduct)
            PaymentProduct(product = PreviewMockData.defaultProduct)
        }
    }
}
