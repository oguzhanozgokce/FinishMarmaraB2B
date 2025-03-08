package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto

data class GetSearchProductResponse(
    @SerializedName("list")
    val list: List<ProductDto>? = null,
)
