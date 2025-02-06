package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class PaginationDto(
    @SerializedName("page")
    val page: Int = 1,

    @SerializedName("size")
    val size: Int = 10,

    @SerializedName("total")
    val total: Int = 0,

    @SerializedName("max")
    val max: Int = 1
)

data class PaginationData<T>(
    @SerialName("list")
    val list: List<T>? = null,

    @SerialName("pagination")
    val pagination: PaginationDto? = null
)
