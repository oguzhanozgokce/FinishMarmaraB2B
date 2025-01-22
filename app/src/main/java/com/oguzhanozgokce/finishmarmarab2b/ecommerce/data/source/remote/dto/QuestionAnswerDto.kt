package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class QuestionAnswerDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("question")
    val question: String? = null,
    @SerializedName("answer")
    val answer: String? = null,
    @SerializedName("created_at")
    val date: String? = null,
    @SerializedName("user_name_surname")
    val userName: String? = null,
    @SerializedName("seller_name")
    val sellerName: String? = null,
    @SerializedName("seller_url")
    val sellerImageUrl: String? = null,
    @SerializedName("pagination")
    val paginationDto: PaginationDto
)
