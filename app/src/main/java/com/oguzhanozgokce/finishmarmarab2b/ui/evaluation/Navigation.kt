package com.oguzhanozgokce.finishmarmarab2b.ui.evaluation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Evaluation

data class EvaluationNavActions(
    val navigateToBack: () -> Unit,
    val navigateToSearch: () -> Unit,
)

fun NavGraphBuilder.evaluation(actions: EvaluationNavActions) {
    composable<Evaluation> {
        val viewModel: EvaluationViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        EvaluationScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            navActions = actions
        )
    }
}
