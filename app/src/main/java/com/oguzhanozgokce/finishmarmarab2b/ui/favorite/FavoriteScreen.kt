package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyFavoriteScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component.CollectionsTabRowList
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component.FMSecondaryTabRow
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component.FavoriteTabRow
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
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
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        FMSecondaryTabRow(
            modifier = Modifier
                .fillMaxWidth(),
            tabs = listOf(
                PrimaryTab(id = 0, title = "Favorites", icon = Icons.Default.Favorite),
                PrimaryTab(id = 1, title = "Collections", icon = Icons.Default.Menu)
            ),
            selectedTabIndex = selectedTabIndex,
            onSelectTab = { index -> selectedTabIndex = index },
            tabContent = { index ->
                when (index) {
                    0 -> FavoriteTabRow(
                        modifier = Modifier,
                        favoriteItems = favoriteItems,
                        onAction = onAction,
                        onNavigateToDetail = onNavigateToDetail
                    )

                    1 -> CollectionsTabRow()
                }
            }
        )
    }
}

@Composable
fun CollectionsTabRow() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        CollectionsTabRowList(
            collectionsList = PreviewMockData.defaultCollection,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp)
        )

        FMButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            text = "Create Collection",
            onClick = { /* todo: implement create collection action */ },
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp)
        )
    }
}

@PreviewLightDark
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
