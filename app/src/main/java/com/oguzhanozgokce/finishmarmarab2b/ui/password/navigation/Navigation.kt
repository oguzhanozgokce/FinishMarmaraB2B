package com.oguzhanozgokce.finishmarmarab2b.ui.password.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.password.ResetPasswordScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.password.ResetPasswordViewModel

fun NavGraphBuilder.password() {
    composable<Screen.ForgotPassword> {
        val viewModel: ResetPasswordViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect

        ResetPasswordScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction
        )
    }
}
