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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Location
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMDeliveryAddress(
    uiState: PaymentContract.UiState,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
    onAddressClick: (Location) -> Unit,
    onEditClick: (Location) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.cardBackground, shape = RoundedCornerShape(padding.dimension8))
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
                text = stringResource(R.string.delivery_address),
                style = FMTheme.typography.titleMediumMedium().copy(
                    fontSize = FMTheme.fontSize.mediumSmall
                )
            )
            TextButton(
                onClick = { onAddClick() }
            ) {
                Text(
                    text = stringResource(R.string.add),
                    style = FMTheme.typography.titleMediumMedium().copy(
                        fontSize = FMTheme.fontSize.small,
                        color = colors.primary
                    )
                )
            }
        }
        if (uiState.locationList.isNotEmpty()) {
            FMHorizontalDivider()
            FMAddressList(
                modifier = Modifier.padding(top = padding.dimension16),
                locations = uiState.locationList,
                onSelected = { onAddressClick(it) },
                selectedLocation = uiState.selectedLocation,
                onEditClick = { onEditClick(it) },
                onDeleteClick = { onDeleteClick(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FMDeliveryAddressPreview() {
    FMTheme {
        FMDeliveryAddress(
            onAddClick = {},
            uiState = PaymentContract.UiState(),
            onAddressClick = {},
            onEditClick = {},
            onDeleteClick = {}
        )
    }
}
