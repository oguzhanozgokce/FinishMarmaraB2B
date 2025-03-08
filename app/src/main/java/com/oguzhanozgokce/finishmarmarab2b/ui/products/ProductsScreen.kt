package com.oguzhanozgokce.finishmarmarab2b.ui.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun ProductsScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navActions: ProductsNavActions,
) {
    when {
        uiState.isLoading -> LoadingBar()
        else -> ProductsContent(uiState = uiState, navActions = navActions)
    }
}

@Composable
fun ProductsContent(
    uiState: UiState,
    navActions: ProductsNavActions
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
        content = { paddingValues ->
            if (uiState.categoryProducts.isEmpty()) {
                EmptyScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colors.background)
                        .padding(paddingValues)
                ) {
                    Text(
                        text = uiState.categoryName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colors.white)
                            .padding(padding.dimension8)
                            .align(Alignment.CenterHorizontally),
                        style = typography.titleMediumMedium().copy(
                            color = colors.primary
                        )
                    )
                    Spacer(modifier = Modifier.height(padding.dimension4))
                    CategoryProductsList(
                        isLoading = uiState.isLoading,
                        products = uiState.categoryProducts,
                        onNavigateToDetail = navActions.navigateToProductDetail,
                        onToggleFavorite = {},
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = colors.background)
                    )
                }
            }
        }
    )
}

@Composable
fun CategoryProductsList(
    isLoading: Boolean,
    products: List<Product>,
    modifier: Modifier = Modifier,
    onNavigateToDetail: (id: Int) -> Unit = {},
    onToggleFavorite: (id: Int) -> Unit
) {
    val listState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        state = listState,
        contentPadding = PaddingValues(padding.dimension8),
    ) {
        if (isLoading) {
            items(6) {
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
