package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMFavoriteButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlinedButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.RatingBar
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun FMCardFavorite(
    product: Product,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onCartClick: () -> Unit
) {
    FMCard(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = padding.dimension8, vertical = padding.dimension4)
            .noRippleClickable { onClick() },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.primaryImageUrl ?: painterResource(id = R.drawable.image_product),
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(padding.dimension8)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(padding.dimension8))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding.dimension8),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = product.title,
                            style = typography.titleMediumMedium(),
                            maxLines = 1
                        )
                        Text(
                            text = product.description,
                            style = typography.bodySmallNormal().copy(
                                color = colors.text.copy(alpha = 0.6f)
                            ),
                            maxLines = 1,
                        )
                    }
                    FMFavoriteButton(
                        isFavorite = product.isFavorite,
                        onClick = onFavoriteClick,
                    )
                }
                Spacer(modifier = Modifier.height(padding.dimension4))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(padding.dimension4)
                ) {
                    Text(
                        text = "${product.rate}",
                        style = typography.titleSmallMedium(),
                        maxLines = 1
                    )
                    RatingBar(rating = product.rate)
                    if (product.commentCount > 0) {
                        Text(
                            text = "(${product.commentCount})",
                            style = typography.titleSmallMedium(),
                            maxLines = 1
                        )
                    }
                }
                Spacer(modifier = Modifier.height(padding.dimension4))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(padding.dimension4)
                ) {
                    Icon(
                        modifier = Modifier.size(padding.dimension16),
                        imageVector = FMTheme.icons.cart,
                        contentDescription = "Cart Icon",
                        tint = colors.primary
                    )
                    Text(
                        text = "230 Kişinin Sepetinde",
                        style = typography.titleSmallMedium(),
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.height(padding.dimension4))
                Text(
                    text = "${product.percentageRate} discount",
                    style = typography.bodySmallLight()
                )
                Spacer(modifier = Modifier.height(padding.dimension4))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(padding.dimension4)
                ) {
                    Text(
                        text = "$${product.price}",
                        style = typography.bodySmallLight(),
                        maxLines = 1,
                        textDecoration = TextDecoration.LineThrough,
                        color = colors.text.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "$${product.discountedPrice}",
                        style = typography.titleBodyBold().copy(
                            color = colors.primary
                        ),
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                FMOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    height = padding.dimension36,
                    text = "Add to Cart",
                    onClick = onCartClick,
                    contentPadding = PaddingValues(padding.dimension4)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFMCardFavorite() {
    FMTheme {
        FMCardFavorite(
            product = PreviewMockData.defaultProduct,
            onClick = {},
            onFavoriteClick = {},
            onCartClick = {}
        )
    }
}
