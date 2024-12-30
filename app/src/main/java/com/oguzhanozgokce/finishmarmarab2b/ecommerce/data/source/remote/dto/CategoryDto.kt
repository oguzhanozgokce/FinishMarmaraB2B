package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("category_Image")
    val categoryImage: String? = null
)
