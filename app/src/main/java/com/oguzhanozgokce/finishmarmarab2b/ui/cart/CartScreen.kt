package com.oguzhanozgokce.finishmarmarab2b.ui.cart

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
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
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMConfirmDialog
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.component.CartBottomBar
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.component.CartProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.component.CartTopBar
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.component.EmptyCartContent
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.navigation.CartNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductListType
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
        onCancel = { onAction(UiAction.HideDialog) },
        onConfirm = {
            onAction(UiAction.DeleteBasketAll)
            onAction(UiAction.HideDialog)
        },
        title = stringResource(R.string.delete_all),
        description = stringResource(R.string.delete_all_products_confirmation),
        confirmText = stringResource(R.string.delete),
        dismissText = stringResource(R.string.cancel)
    )
    CartContent(
        uiState = uiState,
        onAction = onAction,
        cartNavActions = cartNavActions
    )
}

@Composable
fun CartContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    cartNavActions: CartNavActions
) {
    Scaffold(
        topBar = {
            CartTopBar(
                onDeleteClick = { onAction(UiAction.ShowDialog) },
                isLoading = uiState.topLoading,
                isShowIcon = uiState.basketProducts.isNotEmpty(),
                basketCount = uiState.basketProducts.size
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            CartBottomBar(
                totalPrice = uiState.totalPrice,
                onConfirm = cartNavActions.navigateToPayment,
                isEnabled = uiState.basketProducts.isNotEmpty()
            )
        },
        containerColor = colors.background,
        contentColor = colors.black,
    ) { innerPadding ->
        if (uiState.basketProducts.isEmpty()) {
            EmptyCartContent(
                modifier = Modifier.padding(innerPadding),
                onClick = { cartNavActions.navigateToAllProduct(ProductListType.ALL_PRODUCT) }
            )
        } else {
            CartProductList(
                isLoading = uiState.isLoading,
                modifier = Modifier.padding(innerPadding),
                basketProduct = uiState.basketProducts,
                onDeleteBasket = { onAction(UiAction.DeleteBasketProduct(it)) },
                onAddToBasket = { onAction(UiAction.PostProductBasket(it)) },
                onDetail = cartNavActions.navigateToDetail
            )
        }
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
                navigateToPayment = {},
                navigateToAllProduct = {}
            )
        )
    }
}
