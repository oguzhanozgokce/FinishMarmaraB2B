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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Seller
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import java.time.LocalDateTime

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
            defaultElevation = padding.dimension2
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


@Preview(showBackground = true)
@Composable
fun PreviewProductCard() {
    FMTheme {
        val product =
            Product(
                id = 1,
                title = "Wireless Mouse",
                description = "Ergonomic wireless mouse with adjustable DPI settings.",
                price = 25.99,
                discountedPrice = 23.99,
                sellerId = 1,
                stock = 150,
                rate = 4.5,
                categoryId = 101,
                createdAt = LocalDateTime.now().minusDays(5),
                category = Category(
                    id = 101,
                    name = "Electronics",
                    categoryImage = "https://example.com/images/electronics.jpg"
                ),
                seller = Seller(
                    id = 1,
                    name = "TechStore"
                )
            )
        ProductCard(product = product)
    }
}
