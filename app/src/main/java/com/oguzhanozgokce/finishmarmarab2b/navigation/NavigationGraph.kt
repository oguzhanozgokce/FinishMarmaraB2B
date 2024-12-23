package com.oguzhanozgokce.finishmarmarab2b.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen.ForgotPassword
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen.Home
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen.Login
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen.Signup
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen.Welcome
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen.Detail
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.navigation.cart
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.navigation.detail
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.navigation.favorite
import com.oguzhanozgokce.finishmarmarab2b.ui.home.navigation.HomeNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.home.navigation.home
import com.oguzhanozgokce.finishmarmarab2b.ui.login.navigation.LoginNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.login.navigation.login
import com.oguzhanozgokce.finishmarmarab2b.ui.password.navigation.password
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.navigation.payment
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.navigation.profile
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.navigation.SignUpNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.navigation.signUp
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.navigation.WelcomeNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.navigation.welcome


@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        bottomBar = {
            if (Screen.showBottomBar(currentRoute)) {
                FMBottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .then(modifier)
                .padding(innerPadding),
            navController = navController,
            startDestination = Home,
        ) {
            welcome(
                actions = WelcomeNavActions(
                    navigateToLogin = { navController.navigate(route = Login) },
                    navigateToSignup = { navController.navigate(route = Signup) }
                )
            )
            login(
                actions = LoginNavActions(
                    navigateToForgotPassword = { navController.navigate(route = ForgotPassword) },
                    navigateToBack = { navController.navigateUp() },
                    navigateToHome = {
                        navController.navigate(Home) {
                            popUpTo(Welcome) {
                                inclusive = true
                            }
                        }
                    },
                )
            )
            signUp(
                actions = SignUpNavActions(
                    navigateToBack = { navController.navigateUp() },
                    navigateToHome = {
                        navController.navigate(Home) {
                            popUpTo(Welcome) {
                                inclusive = true
                            }
                        }
                    },
                )
            )
            home(
                actions = HomeNavActions(
                    navigateToDetail = { id ->
                        navController.navigate(route = Detail(id))
                    }
                )
            )
            profile()
            favorite()
            cart()
            payment()
            detail()
            password()
        }
    }
}