package com.oguzhanozgokce.finishmarmarab2b.ui.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun PopularSelection(
    popularProduct: List<Product>,
    onPopularItemClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colors.cardBackground)
            .padding(
                top = padding.dimension16,
                start = padding.dimension16,
                end = padding.dimension16,
                bottom = padding.dimension8
            )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Popular Searches",
            style = typography.headMediumSemiBold()
        )
        Spacer(modifier = Modifier.height(padding.dimension8))
        PopularSearchList(
            popularProduct = popularProduct,
            onPopularItemClick = onPopularItemClick
        )
    }
}
