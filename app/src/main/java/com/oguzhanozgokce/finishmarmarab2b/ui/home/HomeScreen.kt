package com.oguzhanozgokce.finishmarmarab2b.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.getFormattedName
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.CategoryList
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMSearch
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.ProductList
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
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
    val categoryItems = uiState.categoryFlow?.collectAsLazyPagingItems()
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowToast -> context.showToast(effect.message)
        }
    }

    HomeContent(
        uiState = uiState,
        homeNavActions = homeNavActions,
        categoryItems = categoryItems,
        onAction = onAction
    )
}

@Composable
fun HomeContent(
    uiState: UiState,
    categoryItems: LazyPagingItems<Category>?,
    homeNavActions: HomeNavActions,
    onAction: (UiAction) -> Unit,
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
            uiState = uiState,
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
                isLoading = uiState.isLoading,
                onNavigateToSearch = { homeNavActions.navigateToSearch() }
            )
            SaleCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .shimmer(uiState.isLoading)
            )
            Text(
                modifier = Modifier.shimmer(uiState.isLoading),
                text = stringResource(id = R.string.categories),
                style = typography.titleBodyBold()
            )
            CategoryList(
                isLoading = uiState.isLoading,
                categoryList = categoryItems,
            )
            ProductText(isLoading = uiState.isLoading)
            ProductList(
                isLoading = uiState.isLoading,
                productList = uiState.productList,
                onNavigateToDetail = homeNavActions.navigateToDetail,
                onToggleFavorite = { onAction(UiAction.ToggleFavorite(it)) }
            )
            Spacer(modifier = Modifier.height(padding.dimension16))
        }
    }
}

@Composable
fun TopBar(
    uiState: UiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier.shimmer(uiState.isLoading),
                text = "Hello,",
                style = typography.titleMediumLight(),
            )
            Text(
                modifier = Modifier.shimmer(uiState.isLoading),
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
                .shimmer(uiState.isLoading)
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
                onClick = {},
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
    isLoading: Boolean = false
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.products),
            style = typography.titleBodyBold(),
            modifier = Modifier.shimmer(isLoading)
        )
        TextButton(
            onClick = { /* TODO: Navigate to all products */ },
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .shimmer(isLoading),
                    text = "See all",
                    style = typography.titleMediumLight()
                )
                HorizontalDivider(
                    modifier = Modifier
                        .width(padding.dimension60)
                        .height(padding.dimension2)
                        .shimmer(isLoading),
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
    FMTheme {
        HomeScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            homeNavActions = HomeNavActions(
                navigateToDetail = {},
                navigateToSearch = {}
            )
        )
    }
}
