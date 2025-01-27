package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class DeleteFavoriteProductRequest(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("product_id")
    val productId: Int
)
