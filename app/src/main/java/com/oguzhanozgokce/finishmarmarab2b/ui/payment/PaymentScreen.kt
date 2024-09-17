package com.oguzhanozgokce.finishmarmarab2b.ui.payment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import com.oguzhanozgokce.finishmarmarab2b.ui.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiState
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Payment Content",
            fontSize = 24.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview(
    @PreviewParameter(PaymentScreenPreviewProvider::class) uiState: UiState,
) {
    PaymentScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
    )
}