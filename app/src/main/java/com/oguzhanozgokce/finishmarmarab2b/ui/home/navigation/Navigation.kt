package com.oguzhanozgokce.finishmarmarab2b.ui.home.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.getActivity
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeViewModel

data class HomeNavActions(
    val navigateToDetail: (id: Int) -> Unit,
    val navigateToSearch: () -> Unit,
    val navigateToProduct: (id: Int, name: String) -> Unit,
)

fun NavGraphBuilder.home(actions: HomeNavActions) {
    composable<Screen.Home> {
        val viewModel: HomeViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        val context = LocalContext.current
        BackHandler {
            context.getActivity()?.finish()
        }
        LaunchedEffect(Unit) {
            viewModel.onAction(HomeContract.UiAction.FetchProduct)
        }
        HomeScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            homeNavActions = actions
        )
    }
}
