package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

import java.time.LocalDateTime

data class Product(
    val productId: Int = 0,
    val title: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val sellerPrice: Double = 0.0,
    val stock: Double = 0.0,
    val rate: Double = 0.0,
    val categoryId: Int = 0,
    val createdTime: LocalDateTime = LocalDateTime.now(),
    val commentId: Int = 0,
    val imageId: Int = 0
)