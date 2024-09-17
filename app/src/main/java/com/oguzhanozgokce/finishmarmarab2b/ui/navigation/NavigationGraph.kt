package com.oguzhanozgokce.finishmarmarab2b.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeViewModel
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginViewModel
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupViewModel
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeViewModel
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.ProfileScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.ProfileViewModel
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteViewModel
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartViewModel
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentViewModel
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = Modifier.then(modifier),
        navController = navController,
        startDestination = "Welcome",
    ) {
        composable("Welcome") {
            val viewModel: WelcomeViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            WelcomeScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                onNavigateToLogin = { navController.navigate("Login") },
                onNavigateToSignup = { navController.navigate("Signup") }
            )
        }
        composable("Login") {
            val viewModel: LoginViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            LoginScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                onNavigateToHome = {
                    navController.navigate("Home") {
                        popUpTo("Welcome") {
                            inclusive = true
                        }
                    }
                },
                onNavigateToForgotPassword = { navController.navigate("ForgotPassword") },
                onNavigateToBack = { navController.navigateUp() }
            )
        }
        composable("Signup") {
            val viewModel: SignupViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            SignupScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                onNavigateToHome = {
                    navController.navigate("Home") {
                        popUpTo("Welcome") {
                            inclusive = true
                        }
                    }
                },
                onNavigateToBack = { navController.navigateUp() }
            )
        }
        composable("Home") {
            val viewModel: HomeViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            HomeScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction
            )
        }
        composable("Profile") {
            val viewModel: ProfileViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            ProfileScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction
            )
        }
        composable("Favorite") {
            val viewModel: FavoriteViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            FavoriteScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction
            )
        }
        composable("Cart") {
            val viewModel: CartViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            CartScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction
            )
        }
        composable("Payment") {
            val viewModel: PaymentViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            PaymentScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction
            )
        }
        composable("Detail") {
            val viewModel: DetailViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            DetailScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction
            )
        }

    }
}