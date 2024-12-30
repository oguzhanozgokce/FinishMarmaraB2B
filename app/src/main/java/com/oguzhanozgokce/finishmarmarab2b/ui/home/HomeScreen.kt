package com.oguzhanozgokce.finishmarmarab2b.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.getFormattedName
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.CategoryList
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.ErrorFooter
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMSearch
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.ProductList
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.home.navigation.HomeNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun HomeScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    homeNavActions: HomeNavActions,
) {
    val context = LocalContext.current
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowToast -> context.showToast(effect.message)
        }
    }
    val productItems = uiState.productFlow?.collectAsLazyPagingItems()
    val refreshState = productItems?.loadState?.refresh
    val itemCount = productItems?.itemCount ?: 0

    when {
        refreshState is LoadState.Loading && itemCount == 0 -> {
            LoadingBar()
        }
        refreshState is LoadState.Error && itemCount == 0 -> {
            ErrorFooter(
                message = refreshState.error.localizedMessage ?: "Unknown error",
                onRetry = { productItems.retry() }
            )
        }
        refreshState !is LoadState.Loading && itemCount == 0 -> {
            EmptyScreen()
        }
        else -> {
            HomeContent(
                uiState = uiState,
                homeNavActions = homeNavActions,
                productItems = productItems
            )
        }
    }
}

@Composable
fun HomeContent(
    uiState: UiState,
    productItems: LazyPagingItems<Product>?,
    homeNavActions: HomeNavActions
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(padding.dimension16)
    ) {
        TopBar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart),
            uiState = uiState
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.dimension80)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(padding.dimension12)
        ) {
            FMSearch(
                modifier = Modifier,
                onNavigateToSearch = {}
            )
            SaleCard(modifier = Modifier.fillMaxWidth())
            Text(
                text = stringResource(id = R.string.categories),
                style = typography.titleBodyBold()
            )
            CategoryList(
                uiState = uiState
            )
            ProductText()
            ProductList(
                productItems = productItems,
                onNavigateToDetail = homeNavActions.navigateToDetail
            )
        }
    }
}

@Composable
fun TopBar(
    uiState: UiState,
    modifier: Modifier = Modifier
) {
    Log.d("HomeScreen", "User name: ${uiState.user.getFormattedName()}")
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Hello,",
                style = typography.titleMediumLight(),
            )
            Text(
                text = uiState.user.getFormattedName(),
                fontFamily = poppinsFontFamily,
                style = typography.labelLargeBold(),
            )
        }
        Icon(
            imageVector = FMTheme.icons.notification,
            contentDescription = null,
            tint = colors.text,
            modifier = Modifier
                .padding(padding.dimension16)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun SaleCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(padding.dimension8),
        colors = CardDefaults.cardColors(
            containerColor = colors.primary
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    horizontal = padding.dimension16,
                    vertical = padding.dimension8
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "SUMMER SALE",
                style = typography.labelLargeBold().copy(
                    color = colors.onText
                ),
            )
            Text(
                text = "50% OFF",
                style = typography.headExtraLargeBold().copy(
                    color = colors.onText
                ),
            )
            Text(
                text = "Amazing discounts on selected items! Don't miss out!",
                style = typography.titleSmallMedium().copy(
                    color = colors.onText
                ),
            )
            TextButton(
                onClick = { /* TODO: Navigate to the sale page */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Explore Now",
                        style = typography.titleMediumSemiBold().copy(
                            color = colors.onText
                        ),
                        modifier = Modifier.padding(bottom = padding.dimension4)
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .width(padding.dimension100)
                            .height(padding.dimension2),
                        thickness = padding.dimension1,
                        color = colors.onText
                    )
                }
            }
        }
    }
}

@Composable
fun ProductText(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.products),
            style = typography.titleBodyBold()
        )
        TextButton(
            onClick = { /* TODO: Navigate to all products */ },
        ) {
            Column {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "See all",
                    style = typography.titleMediumLight()
                )
                HorizontalDivider(
                    modifier = Modifier
                        .width(padding.dimension60)
                        .height(padding.dimension2),
                    thickness = padding.dimension1,
                    color = colors.text
                )
            }
        }
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
        homeNavActions = HomeNavActions(navigateToDetail = {})
    )
}