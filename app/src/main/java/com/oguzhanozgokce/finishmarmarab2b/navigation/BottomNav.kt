package com.oguzhanozgokce.finishmarmarab2b.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    onNavigateToDestination: (BottomBarDestination) -> Unit
) {
    val currentDestination by navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier
            .padding(
                start = padding.dimension16,
                end = padding.dimension16,
                bottom = padding.dimension16
            )
            .clip(RoundedCornerShape(padding.dimension48)),
        containerColor = colors.cardBackground,
        contentColor = colors.text
    ) {
        bottomBarDestination.forEach { destination ->
            val selected = currentDestination?.destination?.route == destination.screen.getRoute()
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = destination.icon(),
                        contentDescription = destination.name,
                        tint = if (selected) colors.primary else colors.text
                    )
                },
                label = null,
                selected = navController.currentDestination?.route == destination.screen.getRoute(),
                onClick = {
                    if (!selected) {
                        onNavigateToDestination(destination)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colors.primary,
                    unselectedIconColor = colors.white,
                    indicatorColor = Color.Transparent
                ),
                alwaysShowLabel = false
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    FMTheme {
        FMBottomBar(navController = rememberNavController(), onNavigateToDestination = {})
    }
}

