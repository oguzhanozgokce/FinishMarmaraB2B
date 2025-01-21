package com.oguzhanozgokce.finishmarmarab2b.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.home.sampleProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CartScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isEmpty() -> EmptyScreen()
        else -> CartContent()
    }
}

@Composable
fun CartContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background),
    ) {
        Text(
            text = "Cart",
            style = FMTheme.typography.headMediumSemiBold(),
        )
        CardProductList()
    }
}

@Composable
fun CardProductList(
    modifier: Modifier = Modifier
) {

}

@Composable
fun CardProductItem(
    modifier: Modifier = Modifier,
    product: Product
) {
    Card(
        modifier = Modifier
            .padding(padding.dimension8)
            .height(padding.dimension140)
            .fillMaxWidth(),
        shape = RoundedCornerShape(padding.dimension16),
        elevation = CardDefaults.cardElevation(
            defaultElevation = padding.dimension4
        ),
        colors = CardDefaults.cardColors(
            containerColor = colors.white
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://picsum.photos/200"),
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
                    .background(Color(0xFFE3E7F1)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(padding.dimension8))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(padding.dimension8),
                verticalArrangement = Arrangement.spacedBy(padding.dimension8)
            ) {
                Text(
                    text = product.title,
                    style = FMTheme.typography.bodyMediumNormal(),
                )
                Text(
                    text = "Amazon",
                    style = FMTheme.typography.bodySmallLight(),
                )
                Text(
                    text = "$${product.price}",
                    style = FMTheme.typography.bodyMediumNormal().copy(
                        fontWeight = FontWeight.Normal,
                        textDecoration = TextDecoration.LineThrough
                    ),
                )
                Text(
                    text = "$${product.discountedPrice}",
                    style = FMTheme.typography.bodyMediumNormal().copy(
                        fontSize = FMTheme.fontSize.body,
                        color = colors.primary
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = padding.dimension16, end = padding.dimension8)
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(alpha = 0.5f))
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Decrease Quantity",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = product.discountedPrice.toString(),
                    style = FMTheme.typography.bodyMediumNormal(),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(alpha = 0.5f))
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase Quantity",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartProductItemPreview() {
    FMTheme {
        CardProductItem(product = sampleProductList.first())
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview(
    @PreviewParameter(CartScreenPreviewProvider::class) uiState: UiState,
) {
    CartScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
    )
}