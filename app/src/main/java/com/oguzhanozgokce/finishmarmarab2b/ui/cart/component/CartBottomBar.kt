package com.oguzhanozgokce.finishmarmarab2b.ui.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import java.text.DecimalFormat

@Composable
fun CartBottomBar(
    modifier: Modifier = Modifier,
    totalPrice: Double = 0.0,
    buttonText: String = "Confirm Cart",
    onConfirm: () -> Unit,
    isEnabled: Boolean = true
) {
    val formatter = DecimalFormat("#,###.00")
    Box(
        modifier = Modifier
            .background(color = colors.cardBackground)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(colors.cardBackground)
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
                    text = "$${formatter.format(totalPrice)}",
                    style = typography.titleMediumSemiBold(),
                )
            }
            FMButton(
                text = buttonText,
                onClick = { onConfirm() },
                contentPadding = PaddingValues(
                    horizontal = padding.dimension16,
                    vertical = padding.dimension8
                ),
                backgroundColor = colors.primary,
                textColor = colors.text,
                height = 40.dp,
                elevation = null,
                enabled = isEnabled
            )
        }
        FMHorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CartBottomBarPreview() {
    FMTheme {
        CartBottomBar(
            totalPrice = 100.0,
            onConfirm = {}
        )
    }
}
