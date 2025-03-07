package com.oguzhanozgokce.finishmarmarab2b.ui.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun CartProductList(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    basketProduct: List<Product>,
    onDeleteBasket: (Int) -> Unit,
    onAddToBasket: (Int) -> Unit,
    onDetail: (Int) -> Unit
) {
    val state = rememberSaveable(saver = LazyListState.Saver) { LazyListState() }

    LazyColumn(
        state = state,
        modifier = modifier
            .fillMaxSize()
            .background(colors.background),
        verticalArrangement = Arrangement.spacedBy(padding.dimension8),
        contentPadding = PaddingValues(vertical = padding.dimension8)
    ) {
        if (isLoading) {
            items(4) {
                CartProductItemShimmer()
            }
        } else {
            items(basketProduct) { product ->
                CartProductItem(
                    product = product,
                    onDeleteBasket = { onDeleteBasket(product.id) },
                    onAddToBasket = { onAddToBasket(product.id) },
                    onDetail = { onDetail(product.id) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartProductListPreview() {
    FMTheme {
        CartProductList(
            basketProduct = PreviewMockData.defaultProductList,
            onDeleteBasket = {},
            onAddToBasket = {},
            onDetail = {},
            isLoading = false
        )
    }
}
