package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Favorite
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteViewModel

fun NavGraphBuilder.favorite(
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToSelectFavorite: (String, Int) -> Unit,
) {
    composable<Favorite> {
        val viewModel: FavoriteViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        LaunchedEffect(Unit) {
            viewModel.onAction(FavoriteContract.UiAction.LoadFavoriteProducts)
        }
        LaunchedEffect(key1 = uiState.isRefreshCollection) {
            if (uiState.isRefreshCollection) {
                viewModel.onAction(FavoriteContract.UiAction.LoadCollections)
                viewModel.updateState { copy(isRefreshCollection = false) }
            }
        }
        FavoriteScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            onNavigateToDetail = onNavigateToDetail,
            onNavigateToSelectFavorite = onNavigateToSelectFavorite
        )
    }
}
