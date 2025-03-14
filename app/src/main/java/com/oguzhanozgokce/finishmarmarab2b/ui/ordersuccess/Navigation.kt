package com.oguzhanozgokce.finishmarmarab2b.ui.ordersuccess

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.OrderSuccess

fun NavGraphBuilder.orderSuccess(
    onNavigateToHome: () -> Unit
) {
    composable<OrderSuccess> {
        val viewModel: OrderSuccessViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        OrderSuccessScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            onNavigateToHome = onNavigateToHome
        )
    }
}