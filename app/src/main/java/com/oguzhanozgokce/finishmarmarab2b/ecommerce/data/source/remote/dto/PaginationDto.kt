package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class PaginationDto(
    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("size")
    val size: Int? = null,

    @SerializedName("total")
    val total: Int? = null,

    @SerializedName("max")
    val max: Int? = null
)

data class PaginationData<T>(
    @SerialName("list")
    val list: List<T>? = null,

    @SerialName("pagination")
    val pagination: PaginationDto? = null
)
