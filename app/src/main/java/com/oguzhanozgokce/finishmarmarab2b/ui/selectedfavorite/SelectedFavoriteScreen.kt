package com.oguzhanozgokce.finishmarmarab2b.ui.selectedfavorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMTopBar
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SelectedFavoriteScreen(
    uiState: SelectedFavoriteContract.UiState,
    uiEffect: Flow<SelectedFavoriteContract.UiEffect>,
    onAction: (SelectedFavoriteContract.UiAction) -> Unit,
    navAction: SelectedFavoriteNavAction
) {
    val context = LocalContext.current
    val favoriteItems = uiState.favoriteList.collectAsLazyPagingItems()

    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is SelectedFavoriteContract.UiEffect.ShowToast -> context.showToast(effect.message)
            is SelectedFavoriteContract.UiEffect.NavigateToBack -> navAction.navigateBack()
        }
    }

    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> SelectedFavoriteContent(
            uiState = uiState,
            onAction = onAction,
            favoriteItems = favoriteItems,
            navAction = navAction
        )
    }
}

@Composable
fun SelectedFavoriteContent(
    uiState: SelectedFavoriteContract.UiState,
    onAction: (SelectedFavoriteContract.UiAction) -> Unit,
    favoriteItems: LazyPagingItems<Product>,
    navAction: SelectedFavoriteNavAction
) {
    Scaffold(
        containerColor = FMTheme.colors.cardBackground,
        topBar = {
            FMTopBar(
                title = "My Favorites",
                onNavigationClick = { navAction.navigateBack() },
                actions = null
            )
        },
        bottomBar = {
            FMButton(
                text = "Add to collection (${uiState.collectionName})",
                onClick = { onAction(SelectedFavoriteContract.UiAction.AddProductToCollection) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = FMTheme.padding.dimension16)
                    .padding(bottom = FMTheme.padding.dimension8),
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SelectFavoriteList(
                favoriteItems = favoriteItems,
                selectedProductIds = uiState.selectedProductIds,
                onClick = { product ->
                    onAction(
                        SelectedFavoriteContract.UiAction.ToggleProductSelection(
                            product.id
                        )
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectedFavoriteScreenPreview(
    @PreviewParameter(SelectedFavoriteScreenPreviewProvider::class) uiState: SelectedFavoriteContract.UiState,
) {
    FMTheme {
        SelectedFavoriteScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            navAction = SelectedFavoriteNavAction(
                navigateBack = {},
                navigateToFavorite = {}
            )
        )
    }
}

class SelectedFavoriteScreenPreviewProvider :
    PreviewParameterProvider<SelectedFavoriteContract.UiState> {
    override val values: Sequence<SelectedFavoriteContract.UiState>
        get() = sequenceOf(
            SelectedFavoriteContract.UiState(
                isLoading = true,
                list = emptyList(),
            ),
            SelectedFavoriteContract.UiState(
                isLoading = false,
                list = emptyList(),
            ),
            SelectedFavoriteContract.UiState(
                isLoading = false,
                list = listOf("Item 1", "Item 2", "Item 3")
            ),
        )
}
