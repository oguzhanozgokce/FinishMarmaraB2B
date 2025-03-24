package com.oguzhanozgokce.finishmarmarab2b.ui.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.ProductCard
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.component.TopBarDetail
import com.oguzhanozgokce.finishmarmarab2b.ui.home.component.ProductCardShimmer
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductsContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductsContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductsContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.products.navigation.ProductsNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

private const val PRODUCT_SHIMMER_COUNT = 6
private const val GRID_COUNT = 2

@Composable
fun ProductsScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navActions: ProductsNavActions,
) {
    when {
        uiState.isLoading -> LoadingBar()
        else -> ProductsContent(
            uiState = uiState,
            onAction = onAction,
            navActions = navActions
        )
    }
}

@Composable
fun ProductsContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    navActions: ProductsNavActions,
) {
    Scaffold(
        topBar = {
            TopBarDetail(
                onBackClick = navActions.navigateToBack,
                onCartClick = navActions.navigateToCart,
                onShareClick = {},
                onSearchClick = navActions.navigateToSearch
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colors.background)
            ) {
                if (uiState.typeList.isEmpty()) {
                    EmptyScreen(
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    ProductsList(
                        isLoading = uiState.isLoading,
                        products = uiState.typeList,
                        onNavigateToDetail = navActions.navigateToProductDetail,
                        onToggleFavorite = { id -> onAction(UiAction.ToggleFavorite(id)) },
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = colors.background)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductsList(
    isLoading: Boolean,
    products: List<Product>,
    modifier: Modifier = Modifier,
    onNavigateToDetail: (id: Int) -> Unit = {},
    onToggleFavorite: (id: Int) -> Unit,
) {
    val listState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(GRID_COUNT),
        modifier = modifier.fillMaxWidth(),
        state = listState,
        contentPadding = PaddingValues(padding.dimension8),
    ) {
        if (isLoading) {
            items(PRODUCT_SHIMMER_COUNT) {
                ProductCardShimmer()
            }
        } else {
            items(
                items = products,
                key = { product -> product.id }
            ) { product ->
                ProductCard(
                    product = product,
                    onNavigateToDetail = { onNavigateToDetail(product.id) },
                    onToggleClick = { onToggleFavorite(product.id) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview(
    @PreviewParameter(ProductsScreenPreviewProvider::class) uiState: UiState,
) {
    FMTheme {
        ProductsScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            navActions = ProductsNavActions(
                navigateToBack = {},
                navigateToCart = {},
                navigateToProductDetail = {},
                navigateToSearch = {}
            )
        )
    }
}
