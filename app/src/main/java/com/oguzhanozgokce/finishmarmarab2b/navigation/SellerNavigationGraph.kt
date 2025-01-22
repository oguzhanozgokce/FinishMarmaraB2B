package com.oguzhanozgokce.finishmarmarab2b.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.oguzhanozgokce.finishmarmarab2b.ui.seller.home.home

@Composable
fun SellerNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = Modifier
            .then(modifier),
        navController = navController,
        startDestination = "SellerWelcome",
    ) {
        home()
    }
}
