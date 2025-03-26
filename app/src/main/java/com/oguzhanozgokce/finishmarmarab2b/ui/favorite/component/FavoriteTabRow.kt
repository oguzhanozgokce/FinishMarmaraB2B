package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyFavoriteScreen
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun FavoriteTabRow(
    modifier: Modifier = Modifier,
    favoriteItems: LazyPagingItems<Product>,
    onAction: (FavoriteContract.UiAction) -> Unit,
    onNavigateToDetail: (Int) -> Unit,
) {
    val isRefreshing = favoriteItems.loadState.refresh is LoadState.Loading
    val isEmpty = favoriteItems.itemSnapshotList.items.isEmpty()
    if (isEmpty && !isRefreshing) {
        EmptyFavoriteScreen()
    } else {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = padding.dimension16,
                        end = padding.dimension8,
                    )
            ) {
                Text(
                    text = "${favoriteItems.itemCount} Favorites",
                    style = typography.bodySmallNormal().copy(
                        fontSize = FMTheme.fontSize.mediumSmall
                    ),
                    textAlign = TextAlign.Center,
                )
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Menu",
                        tint = colors.onBackground
                    )
                }
            }
            FMFavoriteList(
                modifier = Modifier,
                favoriteItems = favoriteItems,
                onFavoriteClick = { id ->
                    onAction(FavoriteContract.UiAction.DeleteFavorite(id))
                },
                onCartClick = { id ->
                    onNavigateToDetail(id)
                },
                onBasketClick = { id, productName ->
                    onAction(FavoriteContract.UiAction.PostProductBasket(id, productName))
                }
            )
        }
    }
}
