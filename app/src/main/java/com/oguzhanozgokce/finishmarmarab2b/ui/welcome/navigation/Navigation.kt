package com.oguzhanozgokce.finishmarmarab2b.ui.welcome.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.getActivity
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeScreen

data class WelcomeNavActions(
    val navigateToLogin: () -> Unit,
    val navigateToSignup: () -> Unit,
)

fun NavGraphBuilder.welcome(actions: WelcomeNavActions) {
    composable<Screen.Welcome> {
        val context = LocalContext.current
        BackHandler {
            context.getActivity()?.finish()
        }
        WelcomeScreen(
            welcomeNavActions = actions
        )
    }
}
