package com.oguzhanozgokce.finishmarmarab2b.ui.cart

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMConfirmDialog
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.component.CartBottomBar
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.component.CartProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.component.CartTopBar
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.navigation.CartNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CartScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    cartNavActions: CartNavActions
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
        onConfirm = {
            onAction(UiAction.DeleteBasketAll)
            onAction(UiAction.HideDialog)
        },
        title = "Delete All",
        description = "Are you sure you want to delete all products from the basket?",
        confirmText = "Delete",
        dismissText = "Cancel"
    )

    when {
        uiState.isLoading -> LoadingBar()
        else -> CartContent(
            uiState = uiState,
            onAction = onAction,
            cartNavActions = cartNavActions
        )
    }
}

@Composable
fun CartContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    cartNavActions: CartNavActions
) {
    Scaffold(
        topBar = { CartTopBar(onDeleteClick = { onAction(UiAction.ShowDialog) }) },
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            CartBottomBar(
                totalPrice = uiState.totalPrice,
                onConfirm = cartNavActions.navigateToPayment
            )
        },
        containerColor = colors.background,
        contentColor = colors.black,
    ) { innerPadding ->
        CartProductList(
            modifier = Modifier.padding(innerPadding),
            basketProduct = uiState.basketProducts,
            onDeleteBasket = { onAction(UiAction.DeleteBasketProduct(it)) },
            onAddToBasket = { onAction(UiAction.PostProductBasket(it)) },
            onDetail = cartNavActions.navigateToDetail
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview(
    @PreviewParameter(CartScreenPreviewProvider::class) uiState: UiState,
) {
    FMTheme {
        CartScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            cartNavActions = CartNavActions(
                navigateToDetail = {},
                navigateToPayment = {}
            )
        )
    }
}
