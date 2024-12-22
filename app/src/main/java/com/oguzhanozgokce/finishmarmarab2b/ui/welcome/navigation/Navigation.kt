package com.oguzhanozgokce.finishmarmarab2b.ui.welcome.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeViewModel

data class WelcomeNavActions(
    val navigateToLogin: () -> Unit,
    val navigateToSignup: () -> Unit,
)

fun NavGraphBuilder.welcome(actions: WelcomeNavActions) {
    composable<Screen.Welcome> {
        val viewModel: WelcomeViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        WelcomeScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            welcomeNavActions = actions
        )
    }
}
