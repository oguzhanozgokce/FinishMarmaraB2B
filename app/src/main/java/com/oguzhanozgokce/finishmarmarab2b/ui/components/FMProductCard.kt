package com.oguzhanozgokce.finishmarmarab2b.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.ProductUi
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract
import com.oguzhanozgokce.finishmarmarab2b.ui.home.sampleProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.dimensions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.typography
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

@Composable
fun ProductCard(
    productUi: ProductUi,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(dimensions.oneHundredEighty)
            .height(dimensions.twoHundredSixty)
            .padding(dimensions.eight),
        shape = RoundedCornerShape(dimensions.eight),
        colors = CardDefaults.cardColors(
            containerColor = colors.lightGray.copy(alpha = 0.3f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensions.two
        )
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(dimensions.oneHundredForty)
                .background(colors.semiTransparentWhite),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = productUi.imageUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            FavoriteButton()
        }
        ProductInfo(
            productUi = productUi,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(dimensions.thirtySix)
            .padding(dimensions.four)
            .background(color = colors.white, shape = CircleShape),
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
            .background(colors.white)
            .padding(dimensions.eight)
    ) {
        Text(
            text = productUi.name,
            fontSize = typography.medium,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFontFamily,
            color = colors.black
        )
        Spacer(modifier = Modifier.height(dimensions.four))
        Text(
            text = "-${productUi.discount}% discount",
            fontWeight = FontWeight.Light,
            fontSize = typography.small,
            fontFamily = poppinsFontFamily,
            color = colors.gray
        )
        Spacer(modifier = Modifier.height(dimensions.eight))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$${productUi.price}",
                fontSize = typography.small,
                fontWeight = FontWeight.Light,
                fontFamily = poppinsFontFamily,
                color = colors.gray,
                textDecoration = TextDecoration.LineThrough
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$${productUi.salePrice}",
                fontSize = typography.large,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFontFamily,
                color = colors.searchIconColor
            )
        }
    }
}

@Composable
fun ProductList(
    uiState: HomeContract.UiState,
    modifier: Modifier = Modifier,
) {
    LazyRow {
        items(uiState.productList) { productItem ->
            ProductCard(
                productUi = productItem,
                modifier = modifier
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
        sellerName = "Seller Name"
    )

    ProductCard(productUi = product)
}
