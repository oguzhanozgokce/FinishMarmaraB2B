package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.mockLazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMFavoriteList(
    isLoading: Boolean = false,
    modifier: Modifier = Modifier,
    favoriteItems: LazyPagingItems<Product>,
    onFavoriteClick: (productId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(padding.dimension8)
    ) {
        if (isLoading) {
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
                    AnimatedFavoriteCard(
                        product = product,
                        onRemoveConfirmed = { productId ->
                            onFavoriteClick(productId)
                        }
                    )
                }
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
            .height(padding.dimension180)
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