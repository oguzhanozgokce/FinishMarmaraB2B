package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import kotlin.math.abs

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onNavigateToDetail: (id: Int) -> Unit = {},
    onToggleClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(padding.dimension200)
            .height(padding.dimension280)
            .padding(
                start = padding.dimension4,
                end = padding.dimension4,
                bottom = padding.dimension8
            )
            .clickable { onNavigateToDetail(product.id) },
        shape = RoundedCornerShape(padding.dimension16),
        colors = CardDefaults.cardColors(
            containerColor = colors.cardBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = padding.dimension1
        )
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(padding.dimension140)
                .background(colors.cardBackground),
            contentAlignment = Alignment.TopEnd
        ) {
            product.images?.takeIf { it.isNotEmpty() && it[0].imageUrl != null }?.let { images ->
                AsyncImage(
                    model = images[0].imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } ?: Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Default Image",
                modifier = Modifier
                    .size(padding.dimension36)
                    .align(Alignment.Center),
                tint = Color.Gray
            )
            FavoriteButton(
                backgroundColor = colors.background,
                boxSize = padding.dimension36,
                isFavorite = product.isFavorite,
                onToggleClick = onToggleClick
            )
        }
        ProductInfo(
            product = product,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    boxSize: Dp,
    isFavorite: Boolean,
    onToggleClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(boxSize)
            .padding(padding.dimension4)
            .background(color = backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (isFavorite) icons.favorite else icons.favoriteBorder,
            contentDescription = null,
            tint = colors.red
        )
    }
}

@Composable
fun ProductInfo(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.cardBackground)
            .padding(padding.dimension8)
    ) {
        Text(
            text = product.title,
            style = typography.titleMediumSemiBold(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(padding.dimension4))
        product.seller?.let { seller ->
            Text(
                text = "Sold by: ${seller.name}",
                style = typography.bodySmallLight(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(padding.dimension4))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(
                rating = product.rate,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(padding.dimension4))
            Text(
                text = "${product.rate}",
                style = typography.bodySmallLight(),
            )
        }
        Spacer(modifier = Modifier.height(padding.dimension4))
        Text(
            text = "-20% discount",
            style = typography.bodySmallLight()
        )
        Spacer(modifier = Modifier.height(padding.dimension4))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$${product.price}",
                style = typography.bodySmallLight(),
                textDecoration = TextDecoration.LineThrough
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$${product.discountedPrice}",
                style = typography.titleBodyBold().copy(
                    color = colors.primary
                ),
            )
        }
    }
}

@Composable
fun ProductList(
    productItems: LazyPagingItems<Product>?,
    modifier: Modifier = Modifier,
    onNavigateToDetail: (id: Int) -> Unit = {},
    onToggleFavorite: (productId: Int, isFavorite: Boolean) -> Unit
) {
    LazyRow(
        modifier = modifier
    ) {
        productItems?.let { lazyItems ->
            items(lazyItems.itemCount) { index ->
                val productItem = lazyItems[index]
                if (productItem != null) {
                    ProductCard(
                        product = productItem,
                        modifier = modifier,
                        onNavigateToDetail = onNavigateToDetail,
                        onToggleClick = {
                            onToggleFavorite(
                                productItem.id,
                                !productItem.isFavorite
                            )
                        }
                    )
                }
            }
            item {
                when (val appendState = lazyItems.loadState.append) {
                    is LoadState.Loading -> {
                        LoadingBarSmall()
                    }

                    is LoadState.Error -> {
                        ErrorFooter(
                            message = appendState.error.localizedMessage ?: "Unknown error",
                            onRetry = { lazyItems.retry() }
                        )
                    }

                    else -> Unit
                }
            }
        }
    }
}

@Composable
fun FMProductList(
    productItems: LazyPagingItems<Product>?,
    modifier: Modifier = Modifier,
    onNavigateToDetail: (id: Int) -> Unit = {},
    onToggleFavorite: (productId: Int, isFavorite: Boolean) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(
        snapLayoutInfoProvider = rememberSnapLayoutInfoProvider(lazyListState)
    )

    LazyRow(
        modifier = modifier,
        state = lazyListState,
        flingBehavior = flingBehavior
    ) {
        productItems?.let { lazyItems ->
            items(lazyItems.itemCount) { index ->
                val productItem = lazyItems[index]
                if (productItem != null) {
                    val offset by remember {
                        derivedStateOf { calculateItemOffset(index, lazyListState) }
                    }
                    val scale = 1f + (0.1f * (1f - offset.coerceIn(0f, 1f)))

                    ProductCard(
                        product = productItem,
                        modifier = Modifier
                            .padding(end = padding.dimension8)
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                alpha = scale
                            },
                        onNavigateToDetail = onNavigateToDetail,
                        onToggleClick = {
                            onToggleFavorite(productItem.id, !productItem.isFavorite)
                        }
                    )
                }
            }

            item {
                when (val appendState = lazyItems.loadState.append) {
                    is LoadState.Loading -> LoadingBarSmall()
                    is LoadState.Error -> ErrorFooter(
                        message = appendState.error.localizedMessage ?: "Unknown error",
                        onRetry = { lazyItems.retry() }
                    )

                    else -> Unit
                }
            }
        }
    }
}

@Composable
fun rememberSnapLayoutInfoProvider(
    state: LazyListState
): SnapLayoutInfoProvider {
    return remember(state) {
        object : SnapLayoutInfoProvider {
            override fun calculateApproachOffset(velocity: Float, decayOffset: Float): Float {
                return decayOffset
            }

            override fun calculateSnapOffset(velocity: Float): Float {
                val layoutInfo = state.layoutInfo
                val viewportCenter = layoutInfo.viewportEndOffset / 2f
                val visibleItems = layoutInfo.visibleItemsInfo

                if (visibleItems.isEmpty()) return 0f

                val nearestItem = visibleItems.minByOrNull { item ->
                    val itemCenter = item.offset + (item.size / 2f)
                    abs(itemCenter - viewportCenter)
                } ?: return 0f

                val centerOfNearest = nearestItem.offset + (nearestItem.size / 2f)
                val distance = viewportCenter - centerOfNearest

                return distance
            }
        }
    }
}

fun calculateItemOffset(
    index: Int,
    state: LazyListState
): Float {
    val visibleItems = state.layoutInfo.visibleItemsInfo
    val currentItem = visibleItems.find { it.index == index }
    return if (currentItem != null) {
        val centerOfItem = currentItem.offset + (currentItem.size / 2f)
        val viewportCenter = state.layoutInfo.viewportEndOffset / 2f
        val distance = abs(viewportCenter - centerOfItem)
        (distance / viewportCenter).coerceIn(0f, 1f)
    } else {
        1f
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductCard() {
    FMTheme {
        val product = PreviewMockData.defaultProduct
        ProductCard(product = product)
    }
}
