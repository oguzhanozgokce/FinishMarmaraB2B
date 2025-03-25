package com.oguzhanozgokce.finishmarmarab2b.ui.selectedfavorite

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme

@Composable
fun SelectFavoriteItem(
    product: Product,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    FMCard(
        modifier = modifier
            .size(120.dp, 200.dp)
            .clickable(onClick = onClick),
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) FMTheme.colors.primary.copy(alpha = 0.5f) else FMTheme.colors.lightGray
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
            ) {
                AsyncImage(
                    model = product.primaryImageUrl
                        ?: painterResource(id = R.drawable.image_product),
                    contentDescription = product.title,
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Crop
                )
                RadioButton(
                    selected = isSelected,
                    onClick = onClick,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(6.dp)
                        .size(20.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = FMTheme.colors.primary,
                        unselectedColor = FMTheme.colors.lightGray,
                        disabledSelectedColor = Color.Unspecified,
                        disabledUnselectedColor = Color.Unspecified
                    )
                )
            }
            Text(
                text = "${product.price}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@PreviewLightDark
@Composable
fun SelectFavoriteItemPreview() {
    FMTheme {
        Column {
            SelectFavoriteItem(
                product = PreviewMockData.defaultProduct,
                onClick = { },
                modifier = Modifier,
                isSelected = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            SelectFavoriteItem(
                product = PreviewMockData.defaultProduct,
                onClick = { },
                modifier = Modifier,
                isSelected = false
            )
        }
    }
}
