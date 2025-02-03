package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("product_Id")
    val productId: Int? = null,
    @SerializedName("image_Url")
    val imageUrl: String? = null
)
