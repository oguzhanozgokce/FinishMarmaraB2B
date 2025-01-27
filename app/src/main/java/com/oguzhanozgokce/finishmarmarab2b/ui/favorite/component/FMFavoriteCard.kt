package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMFavoriteButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlinedButtonWithContent
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMVerticalDivider
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun FMFavoriteCard(
    product: Product,
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit = {}
) {
    FMCard(
        modifier = modifier
            .fillMaxWidth()
            .height(padding.dimension180)
            .padding(padding.dimension8),
        shape = RoundedCornerShape(padding.dimension8),
        elevation = CardDefaults.cardElevation(
            defaultElevation = padding.dimension2
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding.dimension8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductImage(product = product)
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = padding.dimension8)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                ProductDetails(product = product, onFavoriteClick = onFavoriteClick)
                AddToCartButton(product = product)
            }
        }
    }
}

@Composable
fun AnimatedFavoriteCard(
    product: Product,
    onRemoveConfirmed: (Int) -> Unit,
) {
    var isVisible by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = isVisible,
        exit = slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }) + fadeOut(),
    ) {
        FMFavoriteCard(
            product = product,
            onFavoriteClick = {
                isVisible = false
            }
        )
    }
    if (!isVisible) {
        LaunchedEffect(Unit) {
            onRemoveConfirmed(product.id)
        }
    }
}


@Composable
fun RowScope.ProductImage(product: Product) {
    val imageUrl = product.primaryImageUrl
    Image(
        painter = if (imageUrl.isNullOrBlank()) {
            painterResource(id = R.drawable.image_product)
        } else {
            rememberAsyncImagePainter(model = imageUrl)
        },
        contentDescription = "Product Image",
        modifier = Modifier
            .weight(1f)
            .padding(end = padding.dimension8)
            .aspectRatio(3f / 4f)
            .clip(RoundedCornerShape(padding.dimension8)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ProductDetails(
    product: Product,
    onFavoriteClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.title,
                style = typography.titleMediumSemiBold(),
                maxLines = 1
            )
            FMFavoriteButton(
                isFavorite = product.isFavorite,
                onClick = onFavoriteClick,
            )
        }

        Spacer(modifier = Modifier.height(padding.dimension4))
        Text(
            text = product.description,
            style = typography.bodySmallNormal(),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(padding.dimension4))
        product.seller?.let {
            Text(
                text = it.name,
                style = typography.bodySmallNormal(),
                maxLines = 1
            )
        }
    }
}

@Composable
fun AddToCartButton(product: Product) {
    FMOutlinedButtonWithContent(
        onClick = { },
        modifier = Modifier,
        enabled = true,
        elevation = null,
        height = padding.dimension48,
        contentPadding = PaddingValues(horizontal = padding.dimension4),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$${product.price}",
                    style = typography.titleMediumSemiBold().copy(
                        fontSize = fontSize.mediumSmall,
                        color = colors.primary
                    ),
                    maxLines = 1
                )

                FMVerticalDivider(
                    color = colors.black,
                    modifier = Modifier
                        .height(padding.dimension24)
                        .padding(horizontal = padding.dimension4),
                )
                Text(
                    text = stringResource(R.string.add_to_cart),
                    style = typography.titleMediumSemiBold().copy(
                        fontSize = fontSize.mediumSmall,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun FavoriteCardPreview() {
    FMTheme {
        FMFavoriteCard(product = PreviewMockData.defaultProduct, onFavoriteClick = {})
    }
}
