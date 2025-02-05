package com.oguzhanozgokce.finishmarmarab2b.ui.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.component.CartBottomBar
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.FMDeliveryAddress
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.FMPaymentOptions
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.ProductsToBuy
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.TopBarPayment
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun PaymentScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> PaymentContent()
    }
}

@Composable
fun PaymentContent() {
    Scaffold(
        topBar = {
            TopBarPayment()
        },
        bottomBar = {
            CartBottomBar(
                buttonText = "Confirm Payment"
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
                .padding(innerPadding)
                .padding(horizontal = padding.dimension8)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(padding.dimension8)
        ) {
            Spacer(modifier = Modifier.height(padding.dimension8))
            ProductsToBuy()
            FMDeliveryAddress()
            FMPaymentOptions()
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun PaymentScreenPreview(
    @PreviewParameter(PaymentScreenPreviewProvider::class) uiState: UiState,
) {
    FMTheme {
        PaymentScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
        )
    }
}
