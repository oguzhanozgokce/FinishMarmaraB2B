package com.oguzhanozgokce.finishmarmarab2b.ui.payment.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.formatPhoneNumber
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Address
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMAddressItem(
    modifier: Modifier = Modifier,
    address: Address,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    FMCard(
        modifier = modifier
            .size(padding.dimension200, padding.dimension140)
            .noRippleClickable { onSelected() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(
            width = if (isSelected) padding.dimension2 else padding.dimension1,
            color = if (isSelected) colors.primary else colors.primary.copy(alpha = 0.3f)
        ),
        cardColors = CardDefaults.cardColors(containerColor = colors.cardBackground),
        shape = RoundedCornerShape(padding.dimension16)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = padding.dimension12,
                    end = padding.dimension12,
                    top = padding.dimension8
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = padding.dimension4),
                horizontalArrangement = Arrangement.spacedBy(padding.dimension4),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(padding.dimension20),
                    imageVector = Icons.Default.Place,
                    contentDescription = "",
                    tint = colors.primary
                )
                Text(
                    modifier = Modifier,
                    text = address.addressTitle,
                    style = FMTheme.typography.titleMediumMedium().copy(color = colors.primary),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    modifier = Modifier.size(padding.dimension24),
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "",
                        tint = colors.primary
                    )
                }
            }
            Text(
                text = "${address.province} / ${address.city}",
                style = FMTheme.typography.titleMediumMedium().copy(
                    fontSize = FMTheme.fontSize.mediumSmall
                )
            )
            Spacer(modifier = Modifier.height(padding.dimension4))
            Text(
                text = address.openAddress,
                style = FMTheme.typography.titleSmallMedium(),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = padding.dimension8, end = padding.dimension8),
                text = address.addressTel.formatPhoneNumber(),
                style = FMTheme.typography.titleSmallMedium()
            )
        }
    }
}

@Composable
fun FMAddressList(
    modifier: Modifier = Modifier,
    addresses: List<Address>,
    selectedAddress: Address?,
    onSelected: (Address) -> Unit
) {
    LazyRow(
        modifier = modifier.padding(start = padding.dimension8, bottom = padding.dimension8),
        horizontalArrangement = Arrangement.spacedBy(padding.dimension16)
    ) {
        items(addresses) { address ->
            FMAddressItem(
                address = address,
                isSelected = selectedAddress == address,
                onSelected = { onSelected(address) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FMAddressListPreview() {
    FMTheme {
        LazyRow(
            modifier = Modifier
                .background(colors.lightGray)
                .padding(padding.dimension8),
            horizontalArrangement = Arrangement.spacedBy(padding.dimension16)
        ) {
            items(PreviewMockData.defaultAddressList) { address ->
                FMAddressItem(
                    address = address,
                    isSelected = false,
                    onSelected = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FMAddressCardPreview() {
    FMTheme {
        FMAddressItem(
            address = PreviewMockData.defaultAddress,
            isSelected = true,
            onSelected = {}
        )
    }
}
