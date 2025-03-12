package com.oguzhanozgokce.finishmarmarab2b.ui.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMSearchBar
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductListType
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun TopBarSearch(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onClick: () -> Unit = {},
    searchValue: String = "",
    onSearchValueChange: (String) -> Unit = {},
    onSearchClick: (String, ProductListType) -> Unit,
    onClearClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.background)
            .padding(vertical = padding.dimension8)
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = colors.onBackground
            )
        }

        FMSearchBar(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(fraction = 0.75f),
            value = searchValue,
            onValueChange = { onSearchValueChange(it) },
            onClick = onClick,
            onClearClick = onClearClick,
            onSearchClick = onSearchClick
        )

        IconButton(
            onClick = onCartClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "ShoppingCart",
                tint = colors.onBackground
            )
        }
    }
}

@PreviewLightDark
@Composable
fun TopBarSearchPreview() {
    FMTheme {
        TopBarSearch(
            onBackClick = { },
            onCartClick = { },
            searchValue = "Test Search",
            onSearchValueChange = { },
            onSearchClick = { _, _ -> },
            onClearClick = { },
        )
    }
}
