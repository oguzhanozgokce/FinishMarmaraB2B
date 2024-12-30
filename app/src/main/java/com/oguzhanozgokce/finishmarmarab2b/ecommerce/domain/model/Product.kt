package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

import java.time.LocalDateTime

data class Product(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val discountedPrice: Double = 0.0,
    val sellerId: Int = 0,
    val stock: Int = 0,
    val rate: Double = 0.0,
    val categoryId: Int = 0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val category: Category? = null,
    val seller: Seller? = null
)
