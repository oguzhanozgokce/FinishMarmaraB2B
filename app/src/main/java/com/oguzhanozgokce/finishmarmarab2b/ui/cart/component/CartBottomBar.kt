package com.oguzhanozgokce.finishmarmarab2b.ui.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.ui.home.sampleProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun CartBottomBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.white)
            .padding(
                horizontal = padding.dimension8,
                vertical = padding.dimension4
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = padding.dimension8,
                vertical = padding.dimension4
            ),
            verticalArrangement = Arrangement.spacedBy(padding.dimension4)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Total",
                style = typography.titleSmallMedium().copy(
                    fontSize = fontSize.mediumSmall
                ),
            )
            Text(
                text = "$${sampleProductList.sumOf { it.price }}",
                style = typography.titleMediumSemiBold(),
            )
        }
        FMButton(
            text = "Confirm Cart",
            onClick = { },
            contentPadding = PaddingValues(
                horizontal = padding.dimension16,
                vertical = padding.dimension8
            ),
            backgroundColor = colors.primary,
            textColor = colors.white,
            height = 40.dp,
            elevation = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CartBottomBarPreview() {
    FMTheme {
        CartBottomBar()
    }
}
