package com.oguzhanozgokce.finishmarmarab2b.ui.detail.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailViewModel

fun NavGraphBuilder.detail(){
    composable<Screen.Detail> {
        val viewModel: DetailViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect

        DetailScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction
        )
    }
}