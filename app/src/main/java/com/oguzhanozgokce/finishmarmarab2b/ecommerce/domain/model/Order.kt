package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

data class Order(
    val id: Int,
    val userId: Int,
    val locationId: Int,
    val creditCardId: Int,
    val totalPrice: Int,
    val createdAt: String,
    val updatedAt: String
)

data class OrderInfo(
    val id: Int,
    val userId: Int,
    val totalPrice: Double,
    val orderStatus: String,
    val createdAt: String,
    val updatedAt: String,
    val orderImage: List<String>
)
