package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto

data class GetBasketResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("product")
    val product: ProductDto? = null
)
