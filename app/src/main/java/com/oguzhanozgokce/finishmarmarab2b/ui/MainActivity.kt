package com.oguzhanozgokce.finishmarmarab2b.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.navigation.Cart
import com.oguzhanozgokce.finishmarmarab2b.navigation.Favorite
import com.oguzhanozgokce.finishmarmarab2b.navigation.Home
import com.oguzhanozgokce.finishmarmarab2b.navigation.NavigationGraph
import com.oguzhanozgokce.finishmarmarab2b.navigation.Profile
import com.oguzhanozgokce.finishmarmarab2b.ui.splash.SplashViewModel
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by viewModels()
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreen.setKeepOnScreenCondition {
            splashViewModel.isSplashShow.value
        }
        setContent {
            val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
            FMTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val showBottomBar by remember {
                    derivedStateOf {
                        navBackStackEntry?.destination?.hasRoute<Home>() == true ||
                            navBackStackEntry?.destination?.hasRoute<Favorite>() == true ||
                            navBackStackEntry?.destination?.hasRoute<Cart>() == true ||
                            navBackStackEntry?.destination?.hasRoute<Profile>() == true
                    }
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    NavigationGraph(
                        navController = navController,
                        isBottomBarVisible = showBottomBar
                    )
                    if (uiState.isShowNoNetworkDialog) {
                        NoNetworkContent(
                            onRetryClick = { mainViewModel.onAction(MainContract.UiAction.CheckNetworkAgain) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NoNetworkContent(
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.white),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_no_connection),
            contentDescription = null,
            tint = colors.primary,
            modifier = Modifier.size(padding.dimension140)
        )
        Spacer(modifier = Modifier.height(padding.dimension16))
        Text(
            text = "No Internet Connection",
            style = FMTheme.typography.titleMediumMedium().copy(fontSize = fontSize.large)
        )
        Spacer(modifier = Modifier.height(padding.dimension16))
        Text(
            text = "Please check your internet connection and try again.",
            style = FMTheme.typography.titleMediumMedium(),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = padding.dimension8)
        )
        Spacer(modifier = Modifier.height(padding.dimension16))
        FMButton(
            text = "Try again",
            backgroundColor = colors.error.copy(alpha = 0.3f),
            textColor = colors.error,
            elevation = ButtonDefaults.buttonElevation(0.dp),
            onClick = onRetryClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoNetworkContentPreview() {
    FMTheme {
        NoNetworkContent(
            onRetryClick = {}
        )
    }
}
