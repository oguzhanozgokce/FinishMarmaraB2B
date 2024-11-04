package com.oguzhanozgokce.finishmarmarab2b.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.MB2BTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

@Composable
fun ProductCard(
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .width(180.dp)
            .height(260.dp)
            .padding(MB2BTheme.dimensions.eight),
        shape = RoundedCornerShape(MB2BTheme.dimensions.eight),
        colors = CardDefaults.cardColors(
            containerColor = MB2BTheme.colors.lightGray.copy(alpha = 0.3f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = MB2BTheme.dimensions.two
        )
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MB2BTheme.dimensions.eight)
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_product),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    modifier = Modifier.padding(
                        top = MB2BTheme.dimensions.eight,
                        bottom = MB2BTheme.dimensions.four,
                        end = MB2BTheme.dimensions.four,),
                    text = "Product Name",
                    fontFamily = poppinsFontFamily,
                    fontSize = MB2BTheme.typography.small,
                    fontWeight = FontWeight.Medium
                )
            }
        }

    }

}


@Composable
fun ProductList(
    uiState: HomeContract.UiState,
    modifier: Modifier = Modifier,
){

}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview(){
    ProductCard()
}