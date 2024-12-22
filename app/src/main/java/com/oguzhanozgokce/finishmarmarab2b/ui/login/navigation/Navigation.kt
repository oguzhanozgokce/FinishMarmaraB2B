package com.oguzhanozgokce.finishmarmarab2b.ui.login.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginViewModel

data class LoginNavActions(
    val navigateToForgotPassword: () -> Unit,
    val navigateToBack: () -> Unit,
    val navigateToHome: () -> Unit,
)

fun NavGraphBuilder.login(
    actions: LoginNavActions,
) {
    composable<Screen.Login> {
        val viewModel: LoginViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect

        LoginScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            loginNavActions = actions
        )
    }
}

