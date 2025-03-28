package com.oguzhanozgokce.finishmarmarab2b.ui.orderlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMTopBar
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.OrderListContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.OrderListContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.OrderListContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.component.EmptyOrderListContent
import com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.component.OrderItemList
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun OrderListScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navAction: OrderNavAction
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.orderInfoList.isNotEmpty() -> EmptyScreen()
        else -> OrderListContent(
            navAction = navAction,
            uiState = uiState,
        )
    }
}

@Composable
fun OrderListContent(
    uiState: UiState,
    navAction: OrderNavAction
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            FMTopBar(
                title = "Order List",
                onNavigationClick = { navAction.navigationBack() }
            )
        },
        containerColor = FMTheme.colors.background,
        contentColor = FMTheme.colors.onBackground,
    ) { innerPadding ->
        if (uiState.orderInfoList.isEmpty()) {
            EmptyOrderListContent(
                modifier = Modifier.padding(innerPadding),
                onClick = {}
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(FMTheme.colors.background)
                    .padding(innerPadding),
            ) {
                OrderItemList(
                    orders = uiState.orderInfoList,
                    modifier = Modifier.background(color = FMTheme.colors.background),
                    onClick = { }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun OrderListScreenPreview(
    @PreviewParameter(OrderListScreenPreviewProvider::class) uiState: UiState,
) {
    FMTheme {
        OrderListScreen(
            uiState = UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
            navAction = OrderNavAction(
                navigationBack = {},
                navigateToOrderDetails = {}
            )
        )
    }
}
