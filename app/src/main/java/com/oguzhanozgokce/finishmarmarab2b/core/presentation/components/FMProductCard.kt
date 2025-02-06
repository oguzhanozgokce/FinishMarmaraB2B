package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onNavigateToDetail: (id: Int) -> Unit = {},
    onToggleClick: () -> Unit = {}
) {
    FMCard(
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
        cardColors = CardDefaults.cardColors(
            containerColor = colors.cardBackground
        ),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(padding.dimension140)
                .background(colors.cardBackground),
            contentAlignment = Alignment.TopEnd
        ) {
            product.images.takeIf { it.isNotEmpty() }?.let { images ->
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
        )
    }
}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    boxSize: Dp = padding.dimension36,
    isFavorite: Boolean,
    onToggleClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(boxSize)
            .padding(padding.dimension4)
            .background(color = backgroundColor, shape = CircleShape)
            .clickable { onToggleClick() },
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
            text = "${product.percentageRate} discount",
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
    isLoading: Boolean = false,
    productList: List<Product>,
    modifier: Modifier = Modifier,
    onNavigateToDetail: (id: Int) -> Unit = {},
    onToggleFavorite: (productId: Int) -> Unit
) {
    val listState = rememberLazyListState()
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = listState
    ) {
        if (isLoading) {
            items(5) {
                ProductCardShimmer()
            }
        } else {
            items(
                items = productList,
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

@Composable
fun ProductCardShimmer(
    modifier: Modifier = Modifier
) {
    FMCard(
        modifier = modifier
            .width(padding.dimension200)
            .height(padding.dimension280)
            .padding(
                start = padding.dimension4,
                end = padding.dimension4,
                bottom = padding.dimension8
            )
            .shimmer(isLoading = true),
        shape = RoundedCornerShape(padding.dimension16),
        cardColors = CardDefaults.cardColors(
            containerColor = colors.cardBackground
        )
    ) {}
}

@Preview(showBackground = true)
@Composable
fun PreviewProductCard() {
    FMTheme {
        val product = PreviewMockData.defaultProduct
        ProductCard(product = product)
    }
}
