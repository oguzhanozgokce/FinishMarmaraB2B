package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto

data class ProductResponse(
    @SerializedName("list")
    val list: List<ProductDto>,
    @SerializedName("pagination")
    val pagination: PaginationResponse?
)

data class PaginationResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("max")
    val max: Int
)
