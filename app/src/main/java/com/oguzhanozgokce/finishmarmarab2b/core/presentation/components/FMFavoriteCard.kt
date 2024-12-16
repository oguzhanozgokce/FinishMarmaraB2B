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
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.ProductUi
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract
import com.oguzhanozgokce.finishmarmarab2b.ui.home.sampleProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

@Composable
fun FMFavoriteCard(
    productUi: ProductUi,
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
                ProductDetails(productUi)
                AddToCartButton(productUi)
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
fun ProductDetails(productUi: ProductUi) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = productUi.name,
            fontSize = fontSize.medium,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFontFamily,
            color = colors.black,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(padding.dimension4))
        Text(
            text = productUi.description,
            fontSize = fontSize.small,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily,
            color = colors.text,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(padding.dimension4))
        Text(
            text = productUi.sellerName,
            fontSize = fontSize.small,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily,
            color = colors.text,
            maxLines = 1
        )
    }
}

@Composable
fun AddToCartButton(productUi: ProductUi) {
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
                text = "$${productUi.salePrice}",
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
            FMFavoriteCard(productUi = uiState.favoriteList[favoriteItem])
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
    FMFavoriteCard(productUi = product)
}