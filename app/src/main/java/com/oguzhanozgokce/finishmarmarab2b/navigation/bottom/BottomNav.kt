package com.oguzhanozgokce.finishmarmarab2b.navigation.bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

@Composable
fun FMBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val currentDestination by navController.currentBackStackEntryAsState()
    Box(
        modifier = Modifier
            .background(color = colors.background)
    ) {
        NavigationBar(
            modifier = modifier
                .background(color = colors.background)
                .height(72.dp),
            containerColor = colors.cardBackground,
            contentColor = colors.text
        ) {
            bottomBarDestination.forEach { destination ->
                val selected =
                    currentDestination?.destination?.route == Screen.getRoute(destination.screen)
                NavigationBarItem(
                    modifier = Modifier.padding(top = 12.dp),
                    icon = {
                        Icon(
                            imageVector = destination.icon(),
                            contentDescription = destination.name,
                            tint = if (selected) colors.primary else colors.text,
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
                    selected = navController.currentDestination?.route == Screen.getRoute(
                        destination.screen
                    ),
                    onClick = {
                        navController.navigate(Screen.getRoute(destination.screen)) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    enabled = currentDestination?.destination?.route != Screen.getRoute(destination.screen),
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
