package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.mockLazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMFavoriteList(
    modifier: Modifier = Modifier,
    favoriteItems: LazyPagingItems<Product>,
    onFavoriteClick: (productId: Int) -> Unit,
    onCartClick: (productId: Int) -> Unit = {},
) {
    val isRefreshing = favoriteItems.loadState.refresh is LoadState.Loading
    val isAppending = favoriteItems.loadState.append is LoadState.Loading

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(padding.dimension8)
    ) {
        if (isRefreshing) {
            items(5) {
                FMFavoriteCardShimmer(
                    modifier = Modifier.padding(bottom = padding.dimension8)
                )
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
                        onCartClick = {},
                        onClick = { onCartClick(product.id) }
                    )
                }
            }
        }

        if (isAppending) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
fun FMFavoriteCardShimmer(
    modifier: Modifier = Modifier
) {
    FMCard(
        modifier = modifier
            .fillMaxWidth()
            .height(padding.dimension200)
            .padding(padding.dimension8),
        shape = RoundedCornerShape(padding.dimension8),
        elevation = CardDefaults.cardElevation(
            defaultElevation = padding.dimension2
        ),
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteListPreview() {
    FMTheme {
        FMFavoriteList(
            favoriteItems = mockLazyPagingItems(PreviewMockData.defaultProductList),
            onFavoriteClick = {}
        )
    }
}
