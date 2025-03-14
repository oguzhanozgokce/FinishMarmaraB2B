package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class OrderRequest(
    @SerializedName("user_id")
    val userId: Int? = null,
    @SerializedName("location_id")
    val locationId: Int? = null,
    @SerializedName("credit_Card_Id")
    val creditCardId: Int? = null,
    @SerializedName("total_Price")
    val totalPrice: Double? = null,
    @SerializedName("order_Status")
    val orderStatus: String? = null
)
