package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteViewModel

fun NavGraphBuilder.favorite() {
    composable<Screen.Favorite> {
        val viewModel: FavoriteViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect

        FavoriteScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction
        )
    }
}
