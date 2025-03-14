package com.oguzhanozgokce.finishmarmarab2b.ui.ordersuccess

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMAnimatedPreloader
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.ordersuccess.OrderSuccessContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.ordersuccess.OrderSuccessContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.ordersuccess.OrderSuccessContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

private const val WEIGHT_1 = 1f

@Composable
fun OrderSuccessScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToHome: () -> Unit,
) {
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.OnNavigateToHome -> onNavigateToHome()
        }
    }
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> OrderSuccessContent(
            onNavigateToHome = onNavigateToHome
        )
    }
}

@Composable
fun OrderSuccessContent(
    onNavigateToHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FMTheme.colors.background)
            .padding(FMTheme.padding.dimension16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FMAnimatedPreloader(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            lottieFile = R.raw.animation_order_success,
            height = 350.dp
        )
        Spacer(modifier = Modifier.weight(WEIGHT_1))
        FMButton(
            text = "Continue Shopping",
            onClick = { onNavigateToHome() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = FMTheme.padding.dimension16)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSuccessScreenPreview(
) {
    FMTheme {
        OrderSuccessScreen(
            uiState = UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToHome = {}
        )
    }
}
