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
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.dimensions

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
                start = dimensions.sixteen,
                end = dimensions.sixteen,
                bottom = dimensions.sixteen
            )
            .clip(RoundedCornerShape(dimensions.fortyEight)),
        containerColor = colors.semiTransparentWhite,
        contentColor = colors.darkGray
    ) {
        bottomBarDestination.forEach { destination ->
            val selected = currentDestination?.destination?.route == destination.screen.getRoute()
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = destination.icon(),
                        contentDescription = destination.name,
                        tint = if (selected) colors.searchIconColor else colors.darkGray
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
                    selectedIconColor = colors.searchIconColor,
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
    LMTheme {
        FMBottomBar(navController = rememberNavController(), onNavigateToDestination = {})
    }
}

