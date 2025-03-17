package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.CreditCard

data class NavAction(
    val navigateToBack: () -> Unit
)

fun NavGraphBuilder.creditCard(action: NavAction) {
    composable<CreditCard> {
        val viewModel: CreditCardViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        CreditCardScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            navAction = action
        )
    }
}
