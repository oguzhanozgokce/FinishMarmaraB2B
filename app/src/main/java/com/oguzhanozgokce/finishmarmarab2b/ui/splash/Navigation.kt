package com.oguzhanozgokce.finishmarmarab2b.ui.splash

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen

fun NavGraphBuilder.splash(
    onNavigateToHome: () -> Unit,
    onNavigateToWelcome: () -> Unit,
) {
    composable<Screen.Splash> {
        val viewModel = hiltViewModel<SplashViewModel>()
        val uiEffect = viewModel.uiEffect
        SplashScreen(
            uiEffect = uiEffect,
            onNavigateToHome = onNavigateToHome,
            onNavigateToWelcome = onNavigateToWelcome,
        )
    }
}
