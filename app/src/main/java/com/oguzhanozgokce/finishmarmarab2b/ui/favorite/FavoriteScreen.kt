package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyFavoriteScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component.FMFavoriteList
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun FavoriteScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToDetail: (Int) -> Unit
) {
    val context = LocalContext.current
    val favoriteItems = uiState.favoriteList.collectAsLazyPagingItems()
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowToast -> context.showToast(effect.message)
            is UiEffect.Refresh -> favoriteItems.refresh()
        }
    }
    when {
        favoriteItems.itemCount == 0 -> EmptyFavoriteScreen()
        else -> FavoriteContent(
            uiState = uiState,
            favoriteItems = favoriteItems,
            onAction = onAction,
            onNavigateToDetail = onNavigateToDetail
        )
    }
}

@Composable
fun FavoriteContent(
    uiState: UiState,
    favoriteItems: LazyPagingItems<Product>,
    onAction: (UiAction) -> Unit,
    onNavigateToDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = padding.dimension16,
                    end = padding.dimension16,
                    top = padding.dimension16
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(padding.dimension48)
                    .clip(CircleShape)
                    .background(colors.cardBackground)
                    .padding(padding.dimension8),
                tint = Color.LightGray
            )
            Spacer(modifier = Modifier.width(padding.dimension8))
            uiState.user?.let {
                Text(
                    text = it.name,
                    style = typography.titleMediumSemiBold(),
                    modifier = Modifier.shimmer(uiState.isLoading)
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = padding.dimension16, end = padding.dimension8)
        ) {
            Text(
                text = "$0 Favorites",
                style = typography.bodySmallNormal(),
            )
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    tint = colors.black
                )
            }
        }
        FMHorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.dimension8),
        )
        FMFavoriteList(
            modifier = Modifier,
            favoriteItems = favoriteItems,
            onFavoriteClick = { id ->
                onAction(UiAction.DeleteFavorite(id))
            },
            onCartClick = { id ->
                onNavigateToDetail(id)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview(
    @PreviewParameter(FavoriteScreenPreviewProvider::class) uiState: UiState,
) {
    FMTheme {
        FavoriteScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToDetail = {}
        )
    }
}
