package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class PostCollectionRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("user_id")
    val userId: Int,
)
