package com.oguzhanozgokce.finishmarmarab2b.ui.components

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
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.ProductUi
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract
import com.oguzhanozgokce.finishmarmarab2b.ui.home.sampleProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.dimensions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.typography
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

@Composable
fun FMFavoriteCard(
    productUi: ProductUi,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensions.oneHundredEighty)
            .padding(dimensions.eight),
        shape = RoundedCornerShape(dimensions.eight),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensions.four
        ),
        colors = CardDefaults.cardColors(
            containerColor = colors.white
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensions.eight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductImage()
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(dimensions.eight)
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
            .padding(end = dimensions.eight)
            .aspectRatio(3f/4f)
            .clip(RoundedCornerShape(dimensions.eight)),
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
            fontSize = typography.medium,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFontFamily,
            color = colors.black,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(dimensions.four))
        Text(
            text = productUi.description,
            fontSize = typography.small,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily,
            color = colors.gray,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(dimensions.four))
        Text(
            text = productUi.sellerName,
            fontSize = typography.small,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily,
            color = colors.gray,
            maxLines = 1
        )
    }
}

@Composable
fun AddToCartButton(productUi: ProductUi) {
    OutlinedButton(
        onClick = { /* Sepete ekle işlemi */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.white,
            contentColor = colors.searchIconColor
        ),
        shape = RoundedCornerShape(dimensions.twentyFour),
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensions.fortyEight),
        border = BorderStroke(
            width = dimensions.one,
            color = colors.searchIconColor
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "$${productUi.salePrice}",
                fontSize = typography.mediumSmall,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFontFamily,
                color = colors.black
            )
            CustomVerticalDivider(
                color = colors.gray,
                modifier = Modifier
                    .height(dimensions.twentyFour)
                    .padding(horizontal = dimensions.four),
                thickness = dimensions.one
            )
            Text(
                text = stringResource(R.string.add_to_cart),
                fontSize = typography.medium,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily,
                color = colors.searchIconColor,
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
            .padding(dimensions.eight)
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
        sellerName = "Seller Name"
    )
    FMFavoriteCard(productUi = product)
}