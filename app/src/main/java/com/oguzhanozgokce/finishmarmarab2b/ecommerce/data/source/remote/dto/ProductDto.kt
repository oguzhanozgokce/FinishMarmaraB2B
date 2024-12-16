package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("product_id")
    val productId: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("seller_price")
    val sellerPrice: Double? = null,
    @SerializedName("stock")
    val stock: Double? = null,
    @SerializedName("rate")
    val rate: Double? = null,
    @SerializedName("category_id")
    val categoryId: Int? = null,
    @SerializedName("created_time")
    val createdTime: String? = null,
    @SerializedName("comment_id")
    val commentId: Int? = null,
    @SerializedName("image_id")
    val imageId: Int? = null
)


