package com.oguzhanozgokce.finishmarmarab2b.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.oguzhanozgokce.finishmarmarab2b.navigation.FMBottomBar
import com.oguzhanozgokce.finishmarmarab2b.navigation.NavigationGraph
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.navigation.getRoute
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            FMTheme  {
                val navController = rememberNavController()
                val visibleBottomScreens = listOf(
                    Screen.Home.getRoute(),
                    Screen.Favorite.getRoute(),
                    Screen.Cart.getRoute(),
                    Screen.Profile.getRoute()
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colors.white
                ) {
                    AppContent(
                        navController = navController,
                        visibleBottomScreens = visibleBottomScreens
                    )
                }
            }
        }
    }
}

@Composable
fun AppContent(
    navController: NavHostController,
    visibleBottomScreens: List<String>
) {
    val bottomBarVisible = navController
        .currentBackStackEntryAsState()
        .value?.destination?.route in visibleBottomScreens

    Scaffold(
        bottomBar = {
            if (bottomBarVisible) {
                FMBottomBar(
                    navController = navController,
                    onNavigateToDestination = { destination ->
                        navController.navigate(destination.screen.getRoute()) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier
                .background(colors.background)
                .padding(innerPadding)
        )
    }
}