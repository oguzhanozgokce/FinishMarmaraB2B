package com.oguzhanozgokce.finishmarmarab2b.navigation.bottom

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons

sealed class BottomBarDestination(
    val screen: Screen,
    val icon: @Composable () -> ImageVector,
    val name: String
) {
    data object Home : BottomBarDestination(Screen.Home, {icons.home}, "Home")
    data object Favorite : BottomBarDestination(Screen.Favorite, {icons.favorite}, "Favorite")
    data object Cart : BottomBarDestination(Screen.Cart, {icons.cart}, "Cart")
    data object Profile : BottomBarDestination(Screen.Profile, {icons.profile}, "Profile")
}

val bottomBarDestination = listOf(
    BottomBarDestination.Home,
    BottomBarDestination.Favorite,
    BottomBarDestination.Cart,
    BottomBarDestination.Profile
)
