package com.oguzhanozgokce.finishmarmarab2b.navigation.bottom

import android.util.Log
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

@Composable
fun FMBottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val bottomScreens = listOf(
        BottomScreens.HomeBottom,
        BottomScreens.FavoriteBottom,
        BottomScreens.CartBottom,
        BottomScreens.ProfileBottom
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Box(
        modifier = Modifier
            .background(color = colors.background)
    ) {
        NavigationBar(
            modifier = modifier.background(color = colors.background),
            containerColor = colors.cardBackground,
            contentColor = colors.text
        ) {
            bottomScreens.forEach { screen ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == screen.route::class.qualifiedName
                } == true
                Log.d("NavDebug", "Screen: ${screen.route}, isSelected: $isSelected")
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(screen.icon),
                            contentDescription = screen.name,
                            tint = if (isSelected) colors.primary else colors.onBackground,
                            modifier = Modifier.height(20.dp)
                        )
                    },
                    label = {
                        Text(
                            text = screen.name,
                            color = if (isSelected) colors.primary else colors.text,
                            style = FMTheme.typography.bodySmallNormal()
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        Log.d("NavDebug", "Selected screen: $isSelected")
                        if (isSelected) return@NavigationBarItem
                        Log.d("NavDebug", "Navigating to: ${screen.route}")
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    enabled = !isSelected,
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
