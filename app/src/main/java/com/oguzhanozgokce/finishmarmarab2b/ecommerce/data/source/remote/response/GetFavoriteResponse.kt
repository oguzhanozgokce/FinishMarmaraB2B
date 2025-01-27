package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto

data class GetFavoriteResponse(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("user_Id")
    val userId: Int? = null,

    @SerializedName("product_Id")
    val productId: Int? = null,

    @SerializedName("product")
    val product: ProductDto? = null
)
