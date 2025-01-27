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
    val commentCount: Int,
    val categoryId: Int,
    val isFavorite: Boolean,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val category: Category? = null,
    val seller: Seller? = null,
    val images: List<Image>? = null,
) {
    val imageUrl: List<String>
        get() = images?.mapNotNull { it.imageUrl?.takeIf { url -> url.isNotBlank() } }
            ?: emptyList()

    val primaryImageUrl: String?
        get() = imageUrl.firstOrNull()

    val sellerImageUrl: String?
        get() = seller?.imageUrl
}
