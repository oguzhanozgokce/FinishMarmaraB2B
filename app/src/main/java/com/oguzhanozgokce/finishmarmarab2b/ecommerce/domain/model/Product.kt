package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

import java.time.LocalDateTime

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountedPrice: Double,
    val sellerId: Int,
    val stock: Int,
    val rate: Double,
    val categoryId: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val category: Category? = null,
    val seller: Seller? = null,
    val images: List<Image>? = null,
    val isFavorite: Boolean = false
)
