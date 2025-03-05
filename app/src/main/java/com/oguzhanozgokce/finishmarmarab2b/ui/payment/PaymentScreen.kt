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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMConfirmDialog
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.component.CartBottomBar
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.FMAgreementCheckbox
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.FMAgreementDialog
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.FMDeliveryAddress
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.FMPaymentOptions
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.ProductsToBuy
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.TopBarPayment
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.navigation.PaymentNavAction
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
    navAction: PaymentNavAction
) {
    val context = LocalContext.current
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowToast -> context.showToast(effect.message)
        }
    }

    FMConfirmDialog(
        showDialog = uiState.showDialog,
        onDismiss = { onAction(UiAction.HideDialog) },
        onCancel = {
            onAction(UiAction.HideDialog)
            navAction.navigateToBack()
        },
        onConfirm = { onAction(UiAction.HideDialog) },
        title = "Exit Payment",
        description = "Are you sure you want to leave the payment process? You can continue your shopping from where you left off.",
        confirmText = "Continue",
        dismissText = "Exit",
    )

    FMAgreementDialog(
        isShowDialog = uiState.isShowAgreementDialog,
        onDismiss = { onAction(UiAction.HideAgreementDialog) }
    )

    when {
        uiState.isLoading -> LoadingBar()
        uiState.products.isEmpty() -> EmptyScreen()
        else -> PaymentContent(
            uiState = uiState,
            onAction = onAction,
            navAction = navAction
        )
    }
}

@Composable
fun PaymentContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    navAction: PaymentNavAction
) {
    Scaffold(
        topBar = {
            TopBarPayment(onBackClick = { onAction(UiAction.ShowDialog) })
        },
        bottomBar = {
            CartBottomBar(
                buttonText = stringResource(R.string.confirm_payment),
                onConfirm = {},
                totalPrice = uiState.totalPrice
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
            ProductsToBuy(basketProduct = uiState.products, totalCartCount = uiState.totalCartCount)
            FMDeliveryAddress(
                onAddClick = navAction.navigateToAddress,
                uiState = uiState
            )
            FMPaymentOptions(
                uiState = uiState,
                onAction = onAction
            )
            FMAgreementCheckbox(
                isChecked = uiState.isChecked,
                onCheckedChange = { onAction(UiAction.OnCheckAgreement) },
                onShowDialog = { onAction(UiAction.ShowAgreementDialog) }
            )
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
            navAction = PaymentNavAction(
                navigateToBack = {},
                navigateToAddress = {}
            )
        )
    }
}
