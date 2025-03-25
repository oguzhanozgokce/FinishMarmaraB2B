package com.oguzhanozgokce.finishmarmarab2b.ui.editprofile

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.oguzhanozgokce.finishmarmarab2b.navigation.EditProfile

data class EditProfileNavActions(
    val navigateToBack: () -> Unit
)

fun NavGraphBuilder.editProfileNavGraph(actions: EditProfileNavActions) {
    composable<EditProfile> {
        val viewModel: EditProfileViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        EditProfileScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            editProfileNavActions = actions
        )
    }
}