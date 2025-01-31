package com.oguzhanozgokce.finishmarmarab2b.ui.cart

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.component.CartBottomBar
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.component.CartProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.component.CartTopBar
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CartScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        else -> CartContent()
    }
}

@Composable
fun CartContent() {
    Scaffold(
        topBar = { CartTopBar(onDeleteClick = { }) },
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            CartBottomBar()
        },
        containerColor = colors.background,
        contentColor = colors.black,
    ) { innerPadding ->
        CartProductList(
            modifier = Modifier.padding(innerPadding),
            products = PreviewMockData.defaultProductList
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
        )
    }
}
