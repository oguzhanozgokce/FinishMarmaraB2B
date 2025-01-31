package com.oguzhanozgokce.finishmarmarab2b.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.navigateClearingStack
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen.*
import com.oguzhanozgokce.finishmarmarab2b.navigation.bottom.FMBottomBar
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.navigation.cart
import com.oguzhanozgokce.finishmarmarab2b.ui.category.navigation.CategoryNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.category.navigation.categoryProducts
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.navigation.DetailNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.navigation.detail
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.navigation.favorite
import com.oguzhanozgokce.finishmarmarab2b.ui.home.navigation.HomeNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.home.navigation.home
import com.oguzhanozgokce.finishmarmarab2b.ui.login.navigation.LoginNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.login.navigation.login
import com.oguzhanozgokce.finishmarmarab2b.ui.password.navigation.password
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.navigation.payment
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.navigation.profile
import com.oguzhanozgokce.finishmarmarab2b.ui.search.navigation.SearchNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.search.navigation.search
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.navigation.SignUpNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.navigation.signUp
import com.oguzhanozgokce.finishmarmarab2b.ui.splash.splash
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
            startDestination = Splash,
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
                    navigateToHome = { navController.navigateClearingStack(Home, Welcome) },
                )
            )
            signUp(
                actions = SignUpNavActions(
                    navigateToBack = { navController.navigateUp() },
                    navigateToHome = { navController.navigateClearingStack(Login, Welcome) },
                )
            )
            home(
                actions = HomeNavActions(
                    navigateToDetail = { id ->
                        navController.navigate(route = Detail(id))
                    },
                    navigateToSearch = { navController.navigate(route = Search) },
                    navigateToCategory = { id, name ->
                        navController.navigate(route = CategoryProducts(id, name))
                    }
                )
            )
            profile(
                onNavigateToWelcome = { navController.navigateClearingStack(Welcome, Profile) }
            )
            favorite(
                onNavigateToDetail = { id ->
                    navController.navigate(route = Detail(id))
                }
            )
            cart()
            payment()
            detail(
                actions = DetailNavActions(
                    navigateToBack = { navController.navigateUp() },
                    navigateToCart = { navController.navigate(route = Cart) },
                    navigateToSearch = { navController.navigate(route = Search) }
                )
            )
            password()
            search(
                actions = SearchNavActions(
                    navigateToBack = { navController.navigateUp() },
                    navigateToCart = { navController.navigate(route = Cart) }
                )
            )
            splash(
                onNavigateToHome = { navController.navigateClearingStack(Home, Splash) },
                onNavigateToWelcome = { navController.navigateClearingStack(Welcome, Splash) }
            )
            categoryProducts(
                actions = CategoryNavActions(
                    navigateToBack = { navController.navigateUp() },
                    navigateToCart = { navController.navigate(route = Cart) },
                    navigateToSearch = { navController.navigate(route = Search) },
                    navigateToProductDetail = { id ->
                        navController.navigate(route = Detail(id))
                    }
                )
            )
        }
    }
}
