package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BasketData(
    @SerializedName("list")
    val list: List<GetBasketResponse>? = null
)
