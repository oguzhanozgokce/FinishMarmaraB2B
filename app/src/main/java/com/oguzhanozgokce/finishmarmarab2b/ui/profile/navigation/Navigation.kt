package com.oguzhanozgokce.finishmarmarab2b.ui.profile.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Profile
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.ProfileScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.ProfileViewModel

fun NavGraphBuilder.profile(
    onNavigateToWelcome: () -> Unit,
    onNavigateCreditCart: () -> Unit,
) {
    composable<Profile> {
        val viewModel: ProfileViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect

        ProfileScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            onNavigateToWelcome = onNavigateToWelcome,
            onNavigateToCreditCart = onNavigateCreditCart
        )
    }
}
