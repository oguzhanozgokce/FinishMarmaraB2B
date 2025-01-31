package com.oguzhanozgokce.finishmarmarab2b.ui.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.home.sampleProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun ProductCounter(
    product: Product,
    count: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(colors.white)
            .wrapContentSize()
            .padding(padding.dimension4),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = product.primaryImageUrl?.ifBlank { painterResource(id = R.drawable.image_product) },
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(padding.dimension16))
                .border(
                    1.dp,
                    colors.lightGray,
                    RoundedCornerShape(padding.dimension16)
                )
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onClick = {
                    if (count > 1) onDecrease() else onDelete()
                },
                modifier = Modifier.size(16.dp)
            ) {
                Icon(
                    imageVector = if (count > 1) Icons.Default.Delete else Icons.Default.Delete,
                    contentDescription = "Decrease or Delete",
                    tint = colors.primary
                )
            }

            Text(
                text = "$count",
                style = typography.titleMediumMedium()
            )

            IconButton(
                onClick = onIncrease,
                modifier = Modifier.size(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increase",
                    tint = colors.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCounterPreview() {
    FMTheme {
        ProductCounter(
            product = sampleProductList.first(),
            count = 1,
            onIncrease = {},
            onDecrease = {},
            onDelete = {}
        )
    }
}
