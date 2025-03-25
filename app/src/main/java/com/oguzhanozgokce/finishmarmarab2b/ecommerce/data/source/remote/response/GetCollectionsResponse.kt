package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GetCollectionsResponse(
    @SerializedName("list")
    val list: List<CollectionDto>? = null,
)

data class CollectionDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("image_list")
    val imageList: List<String>? = null,
    @SerializedName("product_Count")
    val productCount: Int? = null,
)
