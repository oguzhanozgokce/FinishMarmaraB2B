package com.oguzhanozgokce.finishmarmarab2b.ui.selectedfavorite

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.SelectedFavorite

data class SelectedFavoriteNavAction(
    val navigateToFavorite: () -> Unit,
    val navigateBack: () -> Unit
)

fun NavGraphBuilder.selectedFavorite(actions: SelectedFavoriteNavAction) {
    composable<SelectedFavorite> {
        val viewModel: SelectedFavoriteViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect

        SelectedFavoriteScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            navAction = actions
        )
    }
}
