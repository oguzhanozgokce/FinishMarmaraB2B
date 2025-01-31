package com.oguzhanozgokce.finishmarmarab2b.ui.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun CartProductList(
    modifier: Modifier = Modifier,
    products: List<Product>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background),
        verticalArrangement = Arrangement.spacedBy(padding.dimension8),
        contentPadding = PaddingValues(vertical = padding.dimension8)
    ) {
        items(products) { product ->
            CartProductItem(product = product)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartProductListPreview() {
    FMTheme {
        CartProductList(products = PreviewMockData.defaultProductList)
    }
}
