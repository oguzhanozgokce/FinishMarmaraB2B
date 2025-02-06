package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PostBasketResponse(
    @SerializedName("count")
    val count: Int? = null,
)
