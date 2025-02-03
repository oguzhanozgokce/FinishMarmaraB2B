package com.oguzhanozgokce.finishmarmarab2b.ui.productlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.productlist.ProductListContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.productlist.ProductListContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.productlist.ProductListContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun ProductListScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> ProductListContent()
    }
}

@Composable
fun ProductListContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Detail Content",
            fontSize = 24.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview(
    @PreviewParameter(ProductListScreenPreviewProvider::class) uiState: UiState,
) {
    ProductListScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
    )
}
