package com.oguzhanozgokce.finishmarmarab2b.ui.orderlist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlinedCard
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.OrderInfo
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme

@Composable
fun OrderItem(
    order: OrderInfo,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    FMOutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        cardColors = CardDefaults.outlinedCardColors(
            containerColor = FMTheme.colors.cardBackground,
        ),
        onClick = onClick,
        content = {
            Column(
                modifier = Modifier
                    .background(FMTheme.colors.cardBackground)
                    .padding(FMTheme.padding.dimension8),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier,
                    ) {
                        Text(
                            text = order.createdAt,
                            style = FMTheme.typography.headMediumSemiBold().copy(
                                fontSize = FMTheme.fontSize.mediumSmall
                            ),
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .background(color = FMTheme.colors.cardBackground)
                                .border(
                                    1.dp,
                                    FMTheme.colors.primary,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                tint = FMTheme.colors.primary,
                                modifier = Modifier.size(16.dp),
                                contentDescription = "Rating"
                            )
                            Text(
                                text = "Evaluate",
                                style = FMTheme.typography.bodySmallNormal(),
                                modifier = Modifier.padding(horizontal = 8.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier
                            .background(color = FMTheme.colors.cardBackground)
                            .border(
                                1.dp,
                                FMTheme.colors.onBackground,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Details",
                            style = FMTheme.typography.headMediumSemiBold().copy(
                                fontSize = FMTheme.fontSize.mediumSmall,
                                color = FMTheme.colors.primary
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp),
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            tint = FMTheme.colors.onBackground,
                            modifier = Modifier.size(16.dp),
                            contentDescription = "Navigate to details"
                        )
                    }
                }
                FMHorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = order.orderStatus,
                        style = FMTheme.typography.bodySmallNormal().copy(
                            color = if (order.orderStatus == "Pending") FMTheme.colors.error else Color.Green,
                            fontSize = FMTheme.fontSize.mediumSmall
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Total: ${order.totalPrice}",
                        style = FMTheme.typography.bodySmallNormal().copy(
                            fontSize = FMTheme.fontSize.mediumSmall
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 8.dp,
                            vertical = 4.dp,
                        )
                ) {
                    items(order.orderImage) { image ->
                        AsyncImage(
                            model = image,
                            contentDescription = "Order image",
                            modifier = Modifier
                                .width(80.dp)
                                .height(90.dp)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun OrderItemList(
    orders: List<OrderInfo>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(FMTheme.padding.dimension16),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        state = rememberLazyListState(),
    ) {
        items(orders) { order ->
            OrderItem(
                order = order,
                onClick = { onClick(order.id) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@PreviewLightDark
@Composable
fun OrderItemListPreview() {
    FMTheme {
        OrderItemList(
            orders = PreviewMockData.defaultOrderInfoList,
            modifier = Modifier,
            onClick = { },
        )
    }
}

@PreviewLightDark
@Composable
fun OrderItemPreview() {
    FMTheme {
        OrderItem(
            order = PreviewMockData.defaultOrderInfo,
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
    }
}
