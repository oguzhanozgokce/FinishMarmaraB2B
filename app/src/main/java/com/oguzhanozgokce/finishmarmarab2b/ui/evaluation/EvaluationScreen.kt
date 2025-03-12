package com.oguzhanozgokce.finishmarmarab2b.ui.evaluation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMTopBar
import com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.EvaluationContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.EvaluationContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.EvaluationContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.component.EvaluationBottomDetail
import com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.component.EvaluationList
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun EvaluationScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navActions: EvaluationNavActions
) {
    val context = LocalContext.current
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowToast -> context.showToast(effect.message)
        }
    }

    EvaluationContent(
        uiState = uiState,
        navActions = navActions,
        onAction = onAction
    )
}

@Composable
fun EvaluationContent(
    uiState: UiState,
    navActions: EvaluationNavActions,
    onAction: (UiAction) -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            FMTopBar(
                title = stringResource(R.string.product_evaluation),
                actions = {
                    IconButton(onClick = { navActions.navigateToSearch() }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search),
                            tint = colors.onBackground
                        )
                    }
                },
                onNavigationClick = { navActions.navigateToBack() }
            )
        },
        bottomBar = {
            EvaluationBottomDetail(
                product = PreviewMockData.defaultProduct,
                onAddToCart = { onAction(UiAction.PostProductBasket(it)) }
            )
        },
        containerColor = colors.cardBackground,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(padding.dimension8))
            EvaluationList(
                isLoading = uiState.isLoading,
                questionAnswers = uiState.questionAnswer
            )
        }
    }
}

@PreviewLightDark
@Composable
fun EvaluationScreenPreview(
    @PreviewParameter(EvaluationScreenPreviewProvider::class) uiState: UiState,
) {
    FMTheme {
        EvaluationScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            navActions = EvaluationNavActions(
                navigateToBack = {},
                navigateToSearch = {}
            )
        )
    }
}
