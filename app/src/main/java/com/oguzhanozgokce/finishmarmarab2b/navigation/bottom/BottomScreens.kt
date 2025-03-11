package com.oguzhanozgokce.finishmarmarab2b.navigation.bottom

import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.navigation.Cart
import com.oguzhanozgokce.finishmarmarab2b.navigation.Favorite
import com.oguzhanozgokce.finishmarmarab2b.navigation.Home
import com.oguzhanozgokce.finishmarmarab2b.navigation.Profile

sealed class BottomScreens(
    val route: Any,
    val icon: Int,
    val name: String
) {
    data object HomeBottom : BottomScreens(
        route = Home,
        icon = R.drawable.ic_home,
        name = "Home"
    )

    data object FavoriteBottom : BottomScreens(
        route = Favorite,
        icon = R.drawable.ic_favorite,
        name = "Favorite"
    )

    data object CartBottom : BottomScreens(
        route = Cart,
        icon = R.drawable.ic_cart,
        name = "Cart"
    )

    data object ProfileBottom : BottomScreens(
        route = Profile,
        icon = R.drawable.ic_person,
        name = "Profile"
    )
}
