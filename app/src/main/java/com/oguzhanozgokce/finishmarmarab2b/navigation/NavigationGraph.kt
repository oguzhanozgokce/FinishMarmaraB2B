package com.oguzhanozgokce.finishmarmarab2b.navigation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.navigateClearingStack
import com.oguzhanozgokce.finishmarmarab2b.navigation.bottom.FMBottomBar
import com.oguzhanozgokce.finishmarmarab2b.ui.address.AddressNavAction
import com.oguzhanozgokce.finishmarmarab2b.ui.address.address
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.navigation.CartNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.navigation.cart
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.NavAction
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.creditCard
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.navigation.DetailNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.navigation.detail
import com.oguzhanozgokce.finishmarmarab2b.ui.editprofile.EditProfileNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.editprofile.editProfileNavGraph
import com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.EvaluationNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.evaluation
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.navigation.favorite
import com.oguzhanozgokce.finishmarmarab2b.ui.home.navigation.HomeNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.home.navigation.home
import com.oguzhanozgokce.finishmarmarab2b.ui.login.navigation.LoginNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.login.navigation.login
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.OrderNavAction
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.orderList
import com.oguzhanozgokce.finishmarmarab2b.ui.ordersuccess.orderSuccess
import com.oguzhanozgokce.finishmarmarab2b.ui.password.navigation.password
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.navigation.PaymentNavAction
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.navigation.payment
import com.oguzhanozgokce.finishmarmarab2b.ui.products.navigation.ProductsNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.products.navigation.products
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.navigation.profile
import com.oguzhanozgokce.finishmarmarab2b.ui.search.navigation.SearchNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.search.navigation.search
import com.oguzhanozgokce.finishmarmarab2b.ui.selectedfavorite.SelectedFavoriteNavAction
import com.oguzhanozgokce.finishmarmarab2b.ui.selectedfavorite.selectedFavorite
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.navigation.SignUpNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.navigation.signUp
import com.oguzhanozgokce.finishmarmarab2b.ui.splash.splash
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.navigation.WelcomeNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.navigation.welcome

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isBottomBarVisible: Boolean
) {
    Scaffold(
        modifier = Modifier.imePadding(),
        bottomBar = {
            if (isBottomBarVisible) {
                FMBottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .then(modifier)
                .consumeWindowInsets(innerPadding)
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
                    navigateToCategoryProduct = { id, name, type ->
                        navController.navigate(route = Products(id = id, name = name, type = type))
                    },
                    navigateToAllProduct = { type ->
                        navController.navigate(route = Products(type = type))
                    }
                )
            )
            profile(
                onNavigateToWelcome = { navController.navigateClearingStack(Welcome, Profile) },
                onNavigateCreditCart = { navController.navigate(route = CreditCard) },
                onNavigateToOrderList = { navController.navigate(route = OrderList) },
                onNavigateToEditProfile = { navController.navigate(route = EditProfile) },
            )
            favorite(
                onNavigateToDetail = { id ->
                    navController.navigate(route = Detail(id))
                },
                onNavigateToSelectFavorite = { name, id ->
                    navController.navigate(route = SelectedFavorite(name, id))
                }
            )
            cart(
                actions = CartNavActions(
                    navigateToDetail = { id ->
                        navController.navigate(route = Detail(id))
                    },
                    navigateToPayment = { navController.navigate(route = Payment) },
                    navigateToAllProduct = { type ->
                        navController.navigateClearingStack(route = Products(type = type), Cart)
                    }
                )
            )
            payment(
                navAction = PaymentNavAction(
                    navigateToBack = { navController.navigateUp() },
                    navigateToAddress = { navController.navigate(route = Address.default()) },
                    navigateToEditAddress = {
                        navController.navigate(route = Address(it))
                    },
                    navigateToOrderSuccess = {
                        navController.navigateClearingStack(
                            OrderSuccess,
                            Payment
                        )
                    }
                )
            )

            address(
                navAction = AddressNavAction(
                    navigateToBack = { navController.navigateUp() },
                    navigateToPayment = {
                        navController.navigateClearingStack(
                            Payment,
                            Search
                        )
                    }
                )
            )
            detail(
                actions = DetailNavActions(
                    navigateToBack = { navController.navigateUp() },
                    navigateToCart = { navController.navigate(route = Cart) },
                    navigateToSearch = { navController.navigate(route = Search) },
                    navigationEToEvaluation = { navController.navigate(route = Evaluation) }
                )
            )
            password()
            search(
                actions = SearchNavActions(
                    navigateToBack = { navController.navigateUp() },
                    navigateToCart = { navController.navigate(route = Cart) },
                    navigateToAllProducts = { searchQuery, type ->
                        navController.navigate(
                            route = Products(
                                searchQuery = searchQuery,
                                type = type
                            )
                        )
                    }
                )
            )
            splash(
                onNavigateToHome = { navController.navigateClearingStack(Home, Splash) },
                onNavigateToWelcome = { navController.navigateClearingStack(Welcome, Splash) }
            )
            products(
                actions = ProductsNavActions(
                    navigateToBack = { navController.navigateUp() },
                    navigateToCart = { navController.navigate(route = Cart) },
                    navigateToSearch = { navController.navigate(route = Search) },
                    navigateToProductDetail = { id ->
                        navController.navigate(route = Detail(id))
                    }
                )
            )
            evaluation(
                actions = EvaluationNavActions(
                    navigateToBack = { navController.navigateUp() },
                    navigateToSearch = { navController.navigate(route = Search) }
                )
            )
            orderSuccess(
                onNavigateToHome = { navController.navigateClearingStack(Home, OrderSuccess) }
            )
            creditCard(
                action = NavAction(
                    navigateToBack = { navController.navigateUp() },
                )
            )
            orderList(
                navAction = OrderNavAction(
                    navigationBack = { navController.navigateUp() },
                    navigateToOrderDetails = { }
                )
            )
            selectedFavorite(
                actions = SelectedFavoriteNavAction(
                    navigateBack = { navController.navigateUp() },
                    navigateToFavorite = { navController.navigate(route = Favorite) },
                )
            )
            editProfileNavGraph(
                actions = EditProfileNavActions(
                    navigateToBack = { navController.navigateUp() },
                )
            )
        }
    }
}
