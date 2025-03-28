package com.oguzhanozgokce.finishmarmarab2b.ui.payment.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMIcon
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun ProductsToBuy(
    modifier: Modifier = Modifier,
    totalCartCount: Int = 0,
    basketProduct: List<Product>
) {
    var isExpanded by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.cardBackground, shape = RoundedCornerShape(padding.dimension8))
            .border(
                padding.dimension1,
                colors.primary.copy(alpha = 0.4f),
                shape = RoundedCornerShape(padding.dimension8)
            )
            .padding(horizontal = padding.dimension8)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = padding.dimension8,
                    vertical = padding.dimension8
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Products you will buy ($totalCartCount)",
                style = FMTheme.typography.titleMediumMedium().copy(
                    fontSize = fontSize.mediumSmall
                )
            )
            FMIcon(
                backgroundColor = colors.lightGray.copy(alpha = 0.2f),
                icon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = colors.black
                    )
                },
                boxSize = padding.dimension36,
                onClick = { isExpanded = !isExpanded }
            )
        }
        AnimatedVisibility(
            visible = isExpanded,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding.dimension4)
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding.dimension8),
                horizontalArrangement = Arrangement.spacedBy(padding.dimension4)
            ) {
                items(basketProduct) { basketProduct ->
                    PaymentProduct(product = basketProduct)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsToBuyPreview() {
    FMTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.white),
            verticalArrangement = Arrangement.Center
        ) {
            ProductsToBuy(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                basketProduct = PreviewMockData.defaultProductList
            )
        }
    }
}
