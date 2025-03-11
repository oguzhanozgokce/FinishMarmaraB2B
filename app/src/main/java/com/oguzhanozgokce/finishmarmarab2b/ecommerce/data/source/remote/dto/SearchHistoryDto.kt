package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchHistoryDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("search_Term")
    val searchHistory: String? = null,
)
