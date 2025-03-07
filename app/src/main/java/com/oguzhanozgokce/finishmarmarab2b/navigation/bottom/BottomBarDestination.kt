package com.oguzhanozgokce.finishmarmarab2b.navigation.bottom

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons

sealed class BottomBarDestination(
    val route: Screen,
    val icon: @Composable () -> ImageVector,
    val name: String
) {
    data object Home : BottomBarDestination(
        route = Screen.Home,
        icon = { icons.home },
        name = "Home"
    )

    data object Favorite : BottomBarDestination(
        route = Screen.Favorite,
        icon = { icons.favorite },
        name = "Favorite"
    )

    data object Cart : BottomBarDestination(
        route = Screen.Cart,
        icon = { icons.cart },
        name = "Cart"
    )

    data object Profile : BottomBarDestination(
        route = Screen.Profile,
        icon = { icons.profile },
        name = "Profile"
    )
}

val bottomBarDestination = listOf(
    BottomBarDestination.Home,
    BottomBarDestination.Favorite,
    BottomBarDestination.Cart,
    BottomBarDestination.Profile
)
