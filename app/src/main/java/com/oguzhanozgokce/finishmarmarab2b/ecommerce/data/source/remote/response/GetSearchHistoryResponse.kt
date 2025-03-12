package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.SearchHistoryDto

data class GetSearchHistoryResponse(
    @SerializedName("list")
    val list: List<SearchHistoryDto>? = null,
)
