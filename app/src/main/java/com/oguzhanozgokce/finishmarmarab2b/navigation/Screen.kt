package com.oguzhanozgokce.finishmarmarab2b.navigation

import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductListType
import kotlinx.serialization.Serializable

// inline fun <reified T> NavController.isRouteInBackStack(route: T): Boolean {
//    return try {
//        getBackStackEntry(route = route::class.qualifiedName.toString())
//        true
//    } catch (_: Exception) {
//        false
//    }
// }
// !appNavHostController.isRouteInBackStack(AppScreenRoutes.BottomRoute)

sealed interface Screen {
    @Serializable
    data object Home : Screen

    @Serializable
    data object Profile : Screen

    @Serializable
    data object Favorite : Screen

    @Serializable
    data object Cart : Screen

    @Serializable
    data object Payment : Screen

    @Serializable
    data class Detail(val id: Int) : Screen

    @Serializable
    data object Welcome : Screen

    @Serializable
    data object Login : Screen

    @Serializable
    data object Signup : Screen

    @Serializable
    data object ForgotPassword : Screen

    @Serializable
    data object Search : Screen

    @Serializable
    data object Splash : Screen

    @Serializable
    data object Address : Screen

    @Serializable
    data class Products(
        val id: Int? = null,
        val name: String? = null,
        val searchQuery: String? = null,
        val type: ProductListType,
    ) : Screen

    companion object {
        fun getRoute(screen: Screen): String = screen::class.qualifiedName.orEmpty()

        fun showBottomBar(currentRoute: String?): Boolean {
            return when (currentRoute) {
                getRoute(Home), getRoute(Profile), getRoute(Favorite), getRoute(Cart) -> true
                else -> false
            }
        }
    }
}
