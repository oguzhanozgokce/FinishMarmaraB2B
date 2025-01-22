package com.oguzhanozgokce.finishmarmarab2b.ui.signup.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupViewModel

data class SignUpNavActions(
    val navigateToHome: () -> Unit,
    val navigateToBack: () -> Unit,
)

fun NavGraphBuilder.signUp(actions: SignUpNavActions) {
    composable<Screen.Signup> {
        val viewModel: SignupViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect

        SignupScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            signupNavActions = actions
        )
    }
}
