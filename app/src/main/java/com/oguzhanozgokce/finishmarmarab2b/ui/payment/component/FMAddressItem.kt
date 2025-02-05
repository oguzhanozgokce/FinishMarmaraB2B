package com.oguzhanozgokce.finishmarmarab2b.ui.payment.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.formatPhoneNumber
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMAddressItem(
    modifier: Modifier = Modifier,
    address: FMAddress
) {
    var isSelected by remember { mutableStateOf(false) }
    FMCard(
        modifier = modifier
            .size(padding.dimension180, padding.dimension120)
            .noRippleClickable {
                isSelected = !isSelected
            },
        border = BorderStroke(
            width = padding.dimension1,
            color = if (isSelected) colors.primary else colors.primary.copy(alpha = 0.3f)
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = padding.dimension8,
                    end = padding.dimension8,
                    top = padding.dimension4
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
                    text = address.title,
                    style = FMTheme.typography.titleSmallMedium().copy(
                        color = colors.primary
                    ),
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
                text = "${address.city} / ${address.district}",
                style = FMTheme.typography.titleMediumMedium().copy(
                    fontSize = FMTheme.fontSize.small
                )
            )
            Text(
                text = address.street,
                style = FMTheme.typography.titleMediumMedium().copy(
                    fontSize = FMTheme.fontSize.small
                )
            )
            Text(
                text = address.addressInfo,
                style = FMTheme.typography.titleMediumMedium().copy(
                    fontSize = 10.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier.align(Alignment.End),
                text = address.phoneNumber.formatPhoneNumber(),
                style = FMTheme.typography.titleMediumMedium().copy(
                    fontSize = 10.sp
                )
            )
        }
    }
}

@Composable
fun FMAddressList(
    modifier: Modifier = Modifier,
    addresses: List<FMAddress>
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(padding.dimension8)
    ) {
        items(addresses) { address ->
            FMAddressItem(address = address)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FMAddressCardPreview() {
    FMTheme {
        FMAddressItem(
            address = FMAddress(
                title = "Home",
                city = "İstanbul",
                district = "Beşiktaş",
                street = "Şimalılar",
                addressInfo = "419. Sokak No:6, Daire:12 Beşiktaş İstanbul asdfasdfasdfasdfasfasdfasdfasdfas",
                phoneNumber = "1234567890"
            )
        )
    }
}