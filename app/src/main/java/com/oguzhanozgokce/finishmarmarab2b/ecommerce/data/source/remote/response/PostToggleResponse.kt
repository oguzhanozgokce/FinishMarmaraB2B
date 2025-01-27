package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PostToggleResponse(
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("is_favorite")
    val isFavorite: Boolean
)
