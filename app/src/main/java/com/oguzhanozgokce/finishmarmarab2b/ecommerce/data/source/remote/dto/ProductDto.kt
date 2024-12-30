package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("price")
    val price: Double? = null,

    @SerializedName("discounted_Price")
    val discountedPrice: Double? = null,

    @SerializedName("seller_Id")
    val sellerId: Int? = null,

    @SerializedName("stock")
    val stock: Int? = null,

    @SerializedName("rate")
    val rate: Double? = null,

    @SerializedName("category_Id")
    val categoryId: Int? = null,

    @SerializedName("created_At")
    val createdAt: String? = null,

    @SerializedName("category")
    val category: CategoryDto? = null,

    @SerializedName("seller")
    val seller: SellerDto? = null,

    @SerializedName("pagination")
    val pagination: PaginationDto? = null
)


