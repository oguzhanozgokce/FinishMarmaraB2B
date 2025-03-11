package com.oguzhanozgokce.finishmarmarab2b.ui.address

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Location
import com.oguzhanozgokce.finishmarmarab2b.navigation.Address
import com.oguzhanozgokce.finishmarmarab2b.navigation.CustomNavType
import kotlin.reflect.typeOf

data class AddressNavAction(
    val navigateToBack: () -> Unit,
    val navigateToPayment: () -> Unit
)

fun NavGraphBuilder.address(
    navAction: AddressNavAction
) {
    composable<Address>(
        typeMap = mapOf(typeOf<Location>() to CustomNavType.LocationType)
    ) {
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
