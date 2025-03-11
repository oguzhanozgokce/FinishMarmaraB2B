package com.oguzhanozgokce.finishmarmarab2b.ui.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMAnimatedPreloader
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

@Composable
fun EmptyCartContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        FMAnimatedPreloader(
            modifier = Modifier.size(300.dp),
            lottieFile = R.raw.animation_basket_empty,
            backgroundColor = colors.whiteBlack
        )
        Spacer(modifier = Modifier.height(FMTheme.padding.dimension16))
        Text(
            modifier = Modifier.noRippleClickable { },
            text = "No products in your basket",
            style = FMTheme.typography.bodyMediumNormal().copy(
                color = colors.primary
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(FMTheme.padding.dimension16))
        FMButton(
            text = "Start Shopping",
            onClick = { onClick() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = FMTheme.padding.dimension16),
            contentPadding = PaddingValues(
                horizontal = FMTheme.padding.dimension24,
                vertical = FMTheme.padding.dimension16
            )
        )
    }
}

@PreviewLightDark
@Composable
fun EmptyCartContentPreview() {
    FMTheme {
        EmptyCartContent(onClick = {})
    }
}