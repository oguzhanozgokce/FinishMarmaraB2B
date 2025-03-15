package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("list")
    val list: List<OrderDto>? = null,
    @SerializedName("pagination")
    val paginationDto: PaginationDto
)

data class OrderDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("userId")
    val userId: Int? = null,
    @SerializedName("location_Id")
    val locationId: Int? = null,
    @SerializedName("credit_Card_Id")
    val creditCardId: Int? = null,
    @SerializedName("total_Price")
    val totalPrice: Int? = null,
    @SerializedName("order_Status")
    val orderStatus: String? = null,
    @SerializedName("created_At")
    val createdAt: String? = null,
    @SerializedName("updated_At")
    val updatedAt: String? = null,
)
