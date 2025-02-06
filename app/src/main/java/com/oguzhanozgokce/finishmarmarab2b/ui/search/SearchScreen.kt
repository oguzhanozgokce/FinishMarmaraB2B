package com.oguzhanozgokce.finishmarmarab2b.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SearchScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navActions: SearchNavActions
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        delay(100)
        keyboardController?.show()
    }

    val context = LocalContext.current
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowToast -> {
                context.showToast(effect.message)
            }
        }
    }

    when {
        uiState.isLoading -> LoadingBar()
        else -> SearchContent(
            uiState = uiState,
            onAction = onAction,
            navActions = navActions,
            focusRequester = focusRequester
        )
    }
}

@Composable
fun SearchContent(
    uiState: UiState,
    navActions: SearchNavActions,
    focusRequester: FocusRequester,
    onAction: (UiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background),
    ) {
        TopBarSearch(
            onSearchValueChange = { onAction(UiAction.OnSearchValueChange(value = it)) },
            onBackClick = navActions.navigateToBack,
            onCartClick = navActions.navigateToCart,
            focusRequester = focusRequester
        )
        Spacer(modifier = Modifier.height(padding.dimension8))
        HistorySection(
            historyList = PreviewMockData.historyList,
            onClearAllClick = { },
            onHistoryItemClick = { }
        )
        Spacer(modifier = Modifier.height(padding.dimension8))
        PopularSelection(
            popularProduct = uiState.top5productList,
            onPopularItemClick = { }
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
                navigateToCart = {}
            )
        )
    }
}
