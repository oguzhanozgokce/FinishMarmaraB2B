package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMIcon
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMModelBottomSheet
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMTopBar
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.component.AddCreditCardBottomSheetContent
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.component.CreditCardList
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.component.EmptyCreditCartContent
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditCardScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navAction: NavAction,
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowToast -> context.showToast(effect.message)
        }
    }

    if (uiState.isShowBottomSheet) {
        FMModelBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { onAction(UiAction.HideBottomSheet) },
            content = {
                AddCreditCardBottomSheetContent(
                    uiState = uiState,
                    onAction = onAction,
                    onDismiss = { onAction(UiAction.HideBottomSheet) },
                    onAddCreditCard = { onAction(UiAction.AddCreditCard) }
                )
            },
            dragHandle = null
        )
    }

    when {
        uiState.isLoading -> LoadingBar()
        else -> CreditCardContent(
            uiState = uiState,
            navAction = navAction,
            onAction = onAction,
        )
    }
}

@Composable
fun CreditCardContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    navAction: NavAction
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            FMTopBar(
                title = " Credit Cards ",
                onNavigationClick = { navAction.navigateToBack() },
                actions = {
                    FMIcon(
                        modifier = Modifier.padding(end = FMTheme.padding.dimension16),
                        backgroundColor = FMTheme.colors.primary.copy(alpha = 0.3f),
                        boxSize = FMTheme.padding.dimension36,
                        onClick = { onAction(UiAction.ShowBottomSheet) },
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
        if (uiState.creditCardList.isEmpty()) {
            EmptyCreditCartContent(
                modifier = Modifier.padding(innerPadding),
                onClick = { onAction(UiAction.ShowBottomSheet) }
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(FMTheme.colors.background)
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(FMTheme.padding.dimension16))
                CreditCardList(
                    creditCards = uiState.creditCardList,
                    modifier = Modifier,
                    onItemClick = { },
                    onItemLongClick = { }
                )
            }
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
            navAction = NavAction(
                navigateToBack = {}
            )
        )
    }
}
