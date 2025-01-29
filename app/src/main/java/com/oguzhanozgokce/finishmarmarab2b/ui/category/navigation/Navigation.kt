package com.oguzhanozgokce.finishmarmarab2b.ui.category.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.category.CategoryScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.category.CategoryViewModel

data class CategoryNavActions(
    val navigateToBack: () -> Unit,
    val navigateToCart: () -> Unit,
    val navigateToSearch: () -> Unit,
    val navigateToProductDetail: (Int) -> Unit,
)

fun NavGraphBuilder.categoryProducts(actions: CategoryNavActions) {
    composable<Screen.CategoryProducts> { backStackEntry ->
        backStackEntry.arguments?.getInt("categoryId")
        val viewModel: CategoryViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        CategoryScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            navActions = actions
        )
    }
}
