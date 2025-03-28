package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.order

import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.formatDate
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orDoubleZero
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orZero
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.OrderDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.OrderInfoDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetOrderInfoResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Order
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.OrderInfo

fun OrderDto.mapToOrder(): Order = Order(
    id = id.orZero(),
    userId = userId.orZero(),
    locationId = locationId.orZero(),
    creditCardId = creditCardId.orZero(),
    totalPrice = totalPrice.orZero(),
    createdAt = createdAt.formatDate(),
    updatedAt = updatedAt.formatDate()
)

fun OrderInfoDto.mapToOrderInfo(): OrderInfo = OrderInfo(
    id = this.id.orZero(),
    userId = this.userId.orZero(),
    totalPrice = this.totalPrice.orDoubleZero(),
    createdAt = this.createdAt.formatDate(),
    updatedAt = this.updatedAt.formatDate(),
    orderImage = this.orderImage.orEmpty(),
    orderStatus = this.orderStatus.orEmpty(),
)

fun GetOrderInfoResponse.mapToOrderInfoList(): List<OrderInfo> =
    this.orders.orEmpty().map { it.mapToOrderInfo() }