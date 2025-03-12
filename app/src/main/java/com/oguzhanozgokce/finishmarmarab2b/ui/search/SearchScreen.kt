package com.oguzhanozgokce.finishmarmarab2b.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.search.component.HistorySection
import com.oguzhanozgokce.finishmarmarab2b.ui.search.component.PopularSelection
import com.oguzhanozgokce.finishmarmarab2b.ui.search.component.TopBarSearch
import com.oguzhanozgokce.finishmarmarab2b.ui.search.navigation.SearchNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SearchScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navActions: SearchNavActions,
) {
    val context = LocalContext.current
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowToast -> context.showToast(effect.message)
        }
    }

    when {
        uiState.isLoading -> LoadingBar()
        else -> SearchContent(
            uiState = uiState,
            onAction = onAction,
            navActions = navActions,
        )
    }
}

@Composable
fun SearchContent(
    uiState: UiState,
    navActions: SearchNavActions,
    onAction: (UiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background),
    ) {
        TopBarSearch(
            searchValue = uiState.searchValue,
            onSearchValueChange = { onAction(UiAction.OnSearchValueChange(value = it)) },
            onSearchClick = navActions.navigateToAllProducts,
            onBackClick = navActions.navigateToBack,
            onCartClick = navActions.navigateToCart,
        )
        Spacer(modifier = Modifier.height(padding.dimension8))
        if (uiState.searchHistoryList.isNotEmpty()) {
            HistorySection(
                searchHistoryList = uiState.searchHistoryList,
                onClearAllClick = { onAction(UiAction.DeleteAllSearchHistory) },
                onHistoryItemClick = navActions.navigateToAllProducts,
                onDeleteClick = { onAction(UiAction.DeleteSearchHistory(id = it)) }
            )
        }
        Spacer(modifier = Modifier.height(padding.dimension8))
        PopularSelection(
            popularProduct = uiState.top5productList,
            onPopularItemClick = navActions.navigateToAllProducts
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview(
    @PreviewParameter(SearchScreenPreviewProvider::class) uiState: UiState,
) {
    FMTheme {
        SearchScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            navActions = SearchNavActions(
                navigateToBack = {},
                navigateToCart = {},
                navigateToAllProducts = { _, _ -> }
            )
        )
    }
}
