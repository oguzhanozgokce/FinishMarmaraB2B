package com.oguzhanozgokce.finishmarmarab2b.navigation.bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

fun NavController.navigateTopSingle(
    route: String,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    val defaultNavOptionsBuilder = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setPopUpTo(
            graph.findStartDestination().id,
            inclusive = false,
            saveState = true
        ) // Stack temizliği
        .setRestoreState(true)

    navOptions?.let { userNavOptions ->
        defaultNavOptionsBuilder.apply {
            setEnterAnim(userNavOptions.enterAnim)
            setExitAnim(userNavOptions.exitAnim)
            setPopEnterAnim(userNavOptions.popEnterAnim)
            setPopExitAnim(userNavOptions.popExitAnim)
            setLaunchSingleTop(userNavOptions.shouldLaunchSingleTop())
            setRestoreState(userNavOptions.shouldRestoreState())
        }
    }

    val currentRoute = currentBackStackEntry?.destination?.route
    if (currentRoute != route) {
        navigate(route, defaultNavOptionsBuilder.build(), navigatorExtras)
    }
}

@Composable
fun FMBottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val currentDestination by navController.currentBackStackEntryAsState()
    val defaultNavOptionsBuilder = {
        NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(
                destinationId = navController.graph.findStartDestination().id,
                inclusive = false,
                saveState = true
            )
            .setRestoreState(true)
    }
    Box(
        modifier = Modifier
            .background(color = colors.background)
    ) {
        NavigationBar(
            modifier = modifier
                .background(color = colors.background),
            containerColor = colors.cardBackground,
            contentColor = colors.text
        ) {
            bottomBarDestination.forEach { destination ->
                val targetRoute = Screen.getRoute(destination.route)
                val selected = currentDestination?.destination?.route == targetRoute
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = destination.icon(),
                            contentDescription = destination.name,
                            tint = if (selected) colors.primary else colors.onBackground,
                            modifier = Modifier.height(20.dp)
                        )
                    },
                    label = {
                        Text(
                            text = destination.name,
                            color = if (selected) colors.primary else colors.text,
                            style = FMTheme.typography.bodySmallNormal()
                        )
                    },
                    selected = selected,
                    onClick = {
                        val currentRoute = currentDestination?.destination?.route
                        if (currentRoute != targetRoute) {
                            navController.navigateTopSingle(
                                targetRoute,
                                defaultNavOptionsBuilder().build()
                            )
                        }
                    },
                    enabled = !selected,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colors.primary,
                        unselectedIconColor = colors.white,
                        indicatorColor = Color.Transparent
                    ),
                    alwaysShowLabel = true
                )
            }
        }
        FMHorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    FMTheme {
        FMBottomBar(navController = rememberNavController())
    }
}
