package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

data class Order(
    val id: Int,
    val userId: Int,
    val locationId: Int,
    val creditCardId: Int,
    val totalPrice: Int,
    val orderStatus: String,
    val createdAt: String,
    val updatedAt: String
)
