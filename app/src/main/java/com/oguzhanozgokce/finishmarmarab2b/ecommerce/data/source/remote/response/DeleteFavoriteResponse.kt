package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DeleteFavoriteResponse(
    @SerializedName("product_id")
    val productId: Int
)
