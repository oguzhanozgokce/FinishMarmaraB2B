package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

data class UserComment(
    val id: Int,
    val userName: String,
    val date: String,
    val rating: Double,
    val comment: String,
    val sellerName: String
)
