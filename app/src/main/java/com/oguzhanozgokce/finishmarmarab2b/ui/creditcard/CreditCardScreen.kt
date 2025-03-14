package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMIcon
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMTopBar
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CreditCardScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        else -> CreditCardContent()
    }
}

@Composable
fun CreditCardContent() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            FMTopBar(
                title = " Credit Cards ",
                onNavigationClick = {},
                actions = {
                    FMIcon(
                        modifier = Modifier.padding(end = FMTheme.padding.dimension16),
                        backgroundColor = FMTheme.colors.primary.copy(alpha = 0.3f),
                        boxSize = FMTheme.padding.dimension36,
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = FMTheme.colors.onBackground,
                            )
                        }
                    )
                },
                containerColor = FMTheme.colors.cardBackground,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(FMTheme.colors.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

        }
    }
}

@PreviewLightDark
@Composable
fun CreditCardScreenPreview(
    @PreviewParameter(CreditCardScreenPreviewProvider::class) uiState: UiState,
) {
    FMTheme {
        CreditCardScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
        )
    }
}


