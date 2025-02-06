package com.oguzhanozgokce.finishmarmarab2b.ui.payment.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMDeliveryAddress(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.white, shape = RoundedCornerShape(padding.dimension8))
            .border(
                padding.dimension1,
                colors.lightGray.copy(alpha = 0.4f),
                shape = RoundedCornerShape(padding.dimension8)
            )
            .padding(
                start = padding.dimension8,
                end = padding.dimension8,
                bottom = padding.dimension8
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.dimension8),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Delivery Address",
                style = FMTheme.typography.titleMediumMedium().copy(
                    fontSize = FMTheme.fontSize.mediumSmall
                )
            )
            TextButton(
                onClick = { }
            ) {
                Text(
                    text = "Add",
                    style = FMTheme.typography.titleMediumMedium().copy(
                        fontSize = FMTheme.fontSize.small,
                        color = colors.primary
                    )
                )
            }
        }
        FMHorizontalDivider()
        FMAddressList(
            modifier = Modifier.padding(top = padding.dimension8),
            addresses = PreviewMockData.addressList
        )
    }
}

data class FMAddress(
    val title: String,
    val city: String,
    val district: String,
    val street: String,
    val addressInfo: String,
    val phoneNumber: String
)

@Preview(showBackground = true)
@Composable
fun FMDeliveryAddressPreview() {
    FMTheme {
        FMDeliveryAddress()
    }
}
