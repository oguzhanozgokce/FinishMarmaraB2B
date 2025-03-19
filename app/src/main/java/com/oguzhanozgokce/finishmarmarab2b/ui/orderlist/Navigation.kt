package com.oguzhanozgokce.finishmarmarab2b.ui.orderlist

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.OrderList

data class OrderNavAction(
    val navigationBack: () -> Unit,
    val navigateToOrderDetails: (orderId: Int) -> Unit,
)

fun NavGraphBuilder.orderList(
    navAction: OrderNavAction
) {
    composable<OrderList> {
        val viewModel: OrderListViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        OrderListScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            navAction = navAction
        )
    }
}
