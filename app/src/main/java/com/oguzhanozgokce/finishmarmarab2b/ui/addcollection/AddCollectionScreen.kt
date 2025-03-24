package com.oguzhanozgokce.finishmarmarab2b.ui.addcollection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMTopBar
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.addcollection.AddCollectionContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.addcollection.AddCollectionContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.addcollection.AddCollectionContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun AddCollectionScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> AddCollectionContent()
    }
}

@Composable
fun AddCollectionContent() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            FMTopBar(
                title = "Add Collection",
                onNavigationClick = { },
                containerColor = FMTheme.colors.cardBackground,
                titleContentColor = FMTheme.colors.onPrimary,
                actions = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Add",
                        tint = FMTheme.colors.onPrimary
                    )
                }
            )
        },
        bottomBar = {
            FMButton(
                text = "Add Collection",
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCollectionScreenPreview(
) {
    FMTheme {
        AddCollectionScreen(
            uiState = UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
        )
    }
}

