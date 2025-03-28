package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.OrderInfoDto

data class GetOrderInfoResponse(
    @SerializedName("list")
    val orders: List<OrderInfoDto>? = null
)