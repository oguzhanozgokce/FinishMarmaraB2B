package com.oguzhanozgokce.finishmarmarab2b.ui.selectedfavorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product

private const val LAZY_GRID_SPAN_COUNT = 3

@Composable
fun SelectFavoriteList(
    favoriteItems: LazyPagingItems<Product>,
    selectedProductIds: Set<Int>,
    onClick: (Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(LAZY_GRID_SPAN_COUNT),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = favoriteItems.itemCount,
            key = { index -> favoriteItems[index]?.id ?: index }
        ) { index ->
            val product = favoriteItems[index]
            if (product != null) {
                SelectFavoriteItem(
                    product = product,
                    isSelected = selectedProductIds.contains(product.id),
                    onClick = { onClick(product) }
                )
            }
        }
    }
}
