package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Seller
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract
import com.oguzhanozgokce.finishmarmarab2b.ui.home.sampleProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily
import java.time.LocalDateTime

@Composable
fun FMFavoriteCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(padding.dimension180)
            .padding(padding.dimension8),
        shape = RoundedCornerShape(padding.dimension8),
        elevation = CardDefaults.cardElevation(
            defaultElevation = padding.dimension4
        ),
        colors = CardDefaults.cardColors(
            containerColor = colors.background
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding.dimension8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductImage()
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(padding.dimension8)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                ProductDetails(product)
                AddToCartButton(product)
            }
        }
    }
}

@Composable
fun RowScope.ProductImage() {
    Image(
        painter = painterResource(id = R.drawable.image_product),
        contentDescription = "Product Image",
        modifier = Modifier
            .weight(1f)
            .padding(end = padding.dimension8)
            .aspectRatio(3f/4f)
            .clip(RoundedCornerShape(padding.dimension8)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ProductDetails(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = product.title,
            fontSize = fontSize.medium,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFontFamily,
            color = colors.black,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(padding.dimension4))
        Text(
            text = product.description,
            fontSize = fontSize.small,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily,
            color = colors.text,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(padding.dimension4))
        product.seller?.let {
            Text(
                text = it.name,
                fontSize = fontSize.small,
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFontFamily,
                color = colors.text,
                maxLines = 1
            )
        }
    }
}

@Composable
fun AddToCartButton(product: Product) {
    OutlinedButton(
        onClick = { /* Sepete ekle işlemi */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.background,
            contentColor = colors.primary
        ),
        shape = RoundedCornerShape(padding.dimension24),
        modifier = Modifier
            .fillMaxWidth()
            .height(padding.dimension48),
        border = BorderStroke(
            width = padding.dimension1,
            color = colors.primary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "$${product.price}",
                fontSize = fontSize.mediumSmall,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFontFamily,
                color = colors.black
            )
            CustomVerticalDivider(
                color = colors.black,
                modifier = Modifier
                    .height(padding.dimension24)
                    .padding(horizontal = padding.dimension4),
                thickness = padding.dimension1
            )
            Text(
                text = stringResource(R.string.add_to_cart),
                fontSize = fontSize.medium,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily,
                color = colors.text,
                maxLines = 1
            )
        }
    }
}

@Composable
fun FMFavoriteList(
    modifier: Modifier = Modifier,
    uiState: FavoriteContract.UiState,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(padding.dimension8)
    ) {
        items(uiState.favoriteList.size) { favoriteItem ->
            FMFavoriteCard(product = uiState.favoriteList[favoriteItem])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteListPreview() {
    FMFavoriteList(uiState = FavoriteContract.UiState(favoriteList = sampleProductList))
}

@Preview(showBackground = true)
@Composable
fun FavoriteCardPreview() {
    val product = Product(
        id = 4,
        title = "Noise-Cancelling Headphones",
        description = "Wireless headphones with active noise cancellation.",
        price = 199.99,
        discountedPrice = 189.99,
        sellerId = 4,
        stock = 120,
        rate = 4.6,
        categoryId = 101,
        createdAt = LocalDateTime.now().minusDays(15),
        category = Category(
            id = 101,
            name = "Electronics",
            categoryImage = "https://example.com/images/electronics.jpg"
        ),
        seller = Seller(
            id = 4,
            name = "AudioTech"
        )
    )
    FMFavoriteCard(product = product)
}