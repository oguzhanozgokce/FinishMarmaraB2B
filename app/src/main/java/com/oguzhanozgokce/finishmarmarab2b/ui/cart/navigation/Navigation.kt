package com.oguzhanozgokce.finishmarmarab2b.ui.cart.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartViewModel

data class CartNavActions(
    val navigateToDetail: (id: Int) -> Unit,
    val navigateToPayment: () -> Unit,
)

fun NavGraphBuilder.cart(
    actions: CartNavActions
) {
    composable<Screen.Cart> {
        val viewModel: CartViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        CartScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            cartNavActions = actions
        )
    }
}
