package com.oguzhanozgokce.finishmarmarab2b.ui.search.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductListType
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.search.SearchViewModel

data class SearchNavActions(
    val navigateToBack: () -> Unit,
    val navigateToCart: () -> Unit,
    val navigateToAllProducts: (name: String, type: ProductListType) -> Unit
)

fun NavGraphBuilder.search(actions: SearchNavActions) {
    composable<Screen.Search> {
        val viewModel: SearchViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        SearchScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            navActions = actions
        )
    }
}
