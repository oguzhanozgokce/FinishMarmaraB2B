package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.ProductUi
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract
import com.oguzhanozgokce.finishmarmarab2b.ui.home.sampleProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun ProductCard(
    productUi: ProductUi,
    modifier: Modifier = Modifier,
    onNavigateToDetail: (id: Int) -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(padding.dimension180)
            .height(padding.dimension260)
            .padding(
                start = padding.dimension4,
                end = padding.dimension4,
                bottom = padding.dimension8
            )
            .clickable { onNavigateToDetail(productUi.id) },
        shape = RoundedCornerShape(padding.dimension8),
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
            Image(
                painter = painterResource(id = productUi.imageUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            FavoriteButton(
                backgroundColor = colors.background,
                boxSize = padding.dimension36
            )
        }
        ProductInfo(
            productUi = productUi,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    boxSize: Dp
) {
    Box(
        modifier = modifier
            .size(boxSize)
            .padding(padding.dimension4)
            .background(color = backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icons.favoriteBorder,
            contentDescription = null,
            tint = colors.red
        )
    }
}

@Composable
fun ProductInfo(
    productUi: ProductUi,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.cardBackground)
            .padding(padding.dimension8)
    ) {
        Text(
            text = productUi.name,
            style = typography.titleMediumSemiBold(),
        )
        Spacer(modifier = Modifier.height(padding.dimension4))
        Text(
            text = "-${productUi.discount}% discount",
            style = typography.bodySmallLight()
        )
        Spacer(modifier = Modifier.height(padding.dimension8))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$${productUi.price}",
                style = typography.bodySmallLight(),
                textDecoration = TextDecoration.LineThrough
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$${productUi.salePrice}",
                style = typography.labelLargeBold().copy(
                    color = colors.primary
                ),
            )
        }
    }
}

@Composable
fun ProductList(
    uiState: HomeContract.UiState,
    modifier: Modifier = Modifier,
    onNavigateToDetail: (id: Int) -> Unit = {}
) {
    LazyRow {
        items(sampleProductList) { productItem ->
            ProductCard(
                productUi = productItem,
                modifier = modifier,
                onNavigateToDetail = onNavigateToDetail
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductList() {
    ProductList(uiState = HomeContract.UiState(productList = sampleProductList))
}

@Preview(showBackground = true)
@Composable
fun PreviewProductCard() {
    val product = ProductUi(
        id = 1,
        name = "Perfume",
        price = 75.00.toString(),
        discount = 20,
        salePrice = "60.00",
        description = "Description",
        imageUrl = R.drawable.ic_notification,
        rating = 4.5f,
        sellerName = "Seller Name"
    )

    ProductCard(productUi = product)
}
