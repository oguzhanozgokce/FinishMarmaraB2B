package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyFavoriteScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMModelBottomSheet
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component.AddCollectionBottomSheetContent
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component.CollectionsTabRow
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component.FMSecondaryTabRow
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component.FavoriteTabRow
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.model.PrimaryTab
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToSelectFavorite: (String, Int) -> Unit,
) {
    val context = LocalContext.current
    val favoriteItems = uiState.favoriteList.collectAsLazyPagingItems()
    val sheetState = rememberModalBottomSheetState()
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowToast -> context.showToast(effect.message)
            is UiEffect.Refresh -> favoriteItems.refresh()
            is UiEffect.NavigateToSelectedFavorite -> onNavigateToSelectFavorite(
                effect.collectionName,
                effect.collectionId
            )
        }
    }
    if (uiState.isShowBottomSheet) {
        FMModelBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { onAction(UiAction.HideBottomSheet) },
            content = {
                AddCollectionBottomSheetContent(
                    uiState = uiState,
                    onAction = onAction,
                    onDismissRequest = { onAction(UiAction.HideBottomSheet) },
                    onAddCollection = { onAction(UiAction.PostCollection(it)) }
                )
            },
            dragHandle = null
        )
    }
    when {
        favoriteItems.itemCount == 0 -> EmptyFavoriteScreen()
        else -> FavoriteContent(
            uiState = uiState,
            favoriteItems = favoriteItems,
            onAction = onAction,
            onNavigateToDetail = onNavigateToDetail,
            onCreateCollectionClick = { onAction(UiAction.ShowBottomSheet) }
        )
    }
}

@Composable
fun FavoriteContent(
    uiState: UiState,
    favoriteItems: LazyPagingItems<Product>,
    onAction: (UiAction) -> Unit,
    onNavigateToDetail: (Int) -> Unit,
    onCreateCollectionClick: () -> Unit
) {
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
            selectedTabIndex = uiState.selectedTabIndex,
            onSelectTab = { onAction(UiAction.ToggleSelectedTabIndex(it)) },
            tabContent = { index ->
                when (index) {
                    0 -> FavoriteTabRow(
                        modifier = Modifier,
                        favoriteItems = favoriteItems,
                        onAction = onAction,
                        onNavigateToDetail = onNavigateToDetail
                    )

                    1 -> CollectionsTabRow(
                        collectionsList = uiState.collectionList,
                        onCreateCollectionClick = onCreateCollectionClick
                    )
                }
            }
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
            onNavigateToDetail = {},
            onNavigateToSelectFavorite = { _, _ -> }
        )
    }
}
