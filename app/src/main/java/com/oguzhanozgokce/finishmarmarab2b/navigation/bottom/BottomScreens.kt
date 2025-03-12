package com.oguzhanozgokce.finishmarmarab2b.navigation.bottom

import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.navigation.Cart
import com.oguzhanozgokce.finishmarmarab2b.navigation.Favorite
import com.oguzhanozgokce.finishmarmarab2b.navigation.Home
import com.oguzhanozgokce.finishmarmarab2b.navigation.Profile

data class BottomScreens<T : Any>(val route: T, val icon: Int, val name: String)

val bottomScreens = listOf(
    BottomScreens(Home, R.drawable.ic_home, "Home"),
    BottomScreens(Favorite, R.drawable.ic_favorite, "Favorite"),
    BottomScreens(Cart, R.drawable.ic_cart, "Cart"),
    BottomScreens(Profile, R.drawable.ic_person, "Profile")
)
