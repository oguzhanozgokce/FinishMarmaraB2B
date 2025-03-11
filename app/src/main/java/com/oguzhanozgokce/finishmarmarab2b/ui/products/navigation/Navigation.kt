package com.oguzhanozgokce.finishmarmarab2b.ui.products.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Products
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductsScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductsViewModel

data class ProductsNavActions(
    val navigateToBack: () -> Unit,
    val navigateToCart: () -> Unit,
    val navigateToSearch: () -> Unit,
    val navigateToProductDetail: (Int) -> Unit,
)

fun NavGraphBuilder.products(actions: ProductsNavActions) {
    composable<Products> {
        val viewModel: ProductsViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        ProductsScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            navActions = actions
        )
    }
}
