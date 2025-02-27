package com.oguzhanozgokce.finishmarmarab2b.ui.address

import AddressScreen
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen

data class AddressNavAction(
    val navigateToBack: () -> Unit
)

fun NavGraphBuilder.address(
    navAction: AddressNavAction
) {
    composable<Screen.Address> {
        val viewModel: AddressViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect

        AddressScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            navAction = navAction
        )
    }
}