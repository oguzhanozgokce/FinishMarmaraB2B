package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchHistoryDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("user_id")
    val userId: Int? = null,
    @SerializedName("search_Term")
    val searchHistory: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
)
