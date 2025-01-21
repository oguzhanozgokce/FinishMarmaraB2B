package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class UserCommentDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("user_name_surname")
    val userName: String? = null,
    @SerializedName("created_at")
    val date: String? = null,
    @SerializedName("rate")
    val rating: Double? = null,
    @SerializedName("description")
    val comment: String? = null,
    @SerializedName("sellerName")
    val sellerName: String? = null,
    @SerializedName("pagination")
    val paginationDto: PaginationDto
)
