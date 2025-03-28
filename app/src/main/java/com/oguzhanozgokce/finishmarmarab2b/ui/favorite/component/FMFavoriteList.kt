package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.mockLazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

private const val SIMMER_COUNT = 4

@Composable
fun FMFavoriteList(
    modifier: Modifier = Modifier,
    favoriteItems: LazyPagingItems<Product>,
    onFavoriteClick: (productId: Int) -> Unit,
    onCartClick: (productId: Int) -> Unit,
    onBasketClick: (productId: Int, productName: String) -> Unit,
    onShowBS: (Int) -> Unit
) {
    val isRefreshing = favoriteItems.loadState.refresh is LoadState.Loading
    val isAppending = favoriteItems.loadState.append is LoadState.Loading

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(padding.dimension8)
    ) {
        if (isRefreshing) {
            items(SIMMER_COUNT) {
                FMCardFavoriteShimmer(modifier = Modifier.padding(bottom = padding.dimension8))
            }
        } else {
            items(
                count = favoriteItems.itemCount,
                key = { index -> favoriteItems[index]?.id ?: index }
            ) { index ->
                val product = favoriteItems[index]
                if (product != null) {
                    FMCardFavorite(
                        product = product,
                        onFavoriteClick = { onFavoriteClick(product.id) },
                        onClick = { onCartClick(product.id) },
                        onBasketClick = { onBasketClick(product.id, product.title) },
                        onShowBS = onShowBS
                    )
                }
            }
        }

        if (isAppending) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = padding.dimension16)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteListPreview() {
    FMTheme {
        FMFavoriteList(
            favoriteItems = mockLazyPagingItems(PreviewMockData.defaultProductList),
            onFavoriteClick = {},
            onCartClick = {},
            onBasketClick = { _, _ -> },
            onShowBS = {}
        )
    }
}
