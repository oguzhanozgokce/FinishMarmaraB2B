package com.oguzhanozgokce.finishmarmarab2b.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CategoryList
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomHomeSearch
import com.oguzhanozgokce.finishmarmarab2b.ui.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.components.ProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.MB2BTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun HomeScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> HomeContent(
            uiState = uiState,
        )
    }
}

@Composable
fun HomeContent(
    uiState: UiState,
) {
    Column(
        modifier = Modifier
            .padding(MB2BTheme.dimensions.sixteen)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Welcome Oğuzhan Özgökce",
            fontSize = MB2BTheme.typography.medium,
            fontWeight = FontWeight.Bold,
            color = MB2BTheme.colors.darkGray
        )
        Spacer(modifier = Modifier.height(MB2BTheme.dimensions.sixteen))
        CustomHomeSearch(
            modifier = Modifier,
            onNavigateToSearch = {}
        )
        Spacer(modifier = Modifier.height(MB2BTheme.dimensions.sixteen))
        Text(
            text = stringResource(id = R.string.categories),
            fontSize = MB2BTheme.typography.medium,
            fontWeight = FontWeight.Bold,
            color = MB2BTheme.colors.darkGray
        )
        CategoryList(
            uiState = uiState
        )
        Text(
            text = stringResource(id = R.string.products),
            fontSize = MB2BTheme.typography.medium,
            fontWeight = FontWeight.Bold,
            color = MB2BTheme.colors.darkGray
        )
        ProductList(
            uiState = uiState
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(
    @PreviewParameter(HomeScreenPreviewProvider::class) uiState: UiState,
) {
    HomeScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
    )
}