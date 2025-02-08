package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

import java.time.LocalDateTime

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountedPrice: Double,
    val percentageRate: String,
    val sellerId: Int,
    val stock: Int,
    val rate: Double,
    val commentCount: Int,
    val favoriteCount: Int,
    val basketCount: Int,
    val categoryId: Int,
    val isFavorite: Boolean,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val category: Category,
    val seller: Seller,
    val images: List<Image>,
    val count: Int = 1,
) {
    val imageUrl: List<String>
        get() = images.map { it.imageUrl }

    val primaryImageUrl: String?
        get() = imageUrl.firstOrNull()

    val sellerImageUrl: String
        get() = seller.imageUrl

    val totalPrice: Double
        get() = discountedPrice * count
}
