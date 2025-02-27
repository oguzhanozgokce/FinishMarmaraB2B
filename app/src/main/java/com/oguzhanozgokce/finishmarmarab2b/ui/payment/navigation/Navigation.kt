package com.oguzhanozgokce.finishmarmarab2b.ui.payment.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentViewModel

data class PaymentNavAction(
    val navigateToBack: () -> Unit,
    val navigateToAddress: () -> Unit
)

fun NavGraphBuilder.payment(
    navAction: PaymentNavAction
) {
    composable<Screen.Payment> {
        val viewModel: PaymentViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect

        PaymentScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            navAction = navAction
        )
    }
}
