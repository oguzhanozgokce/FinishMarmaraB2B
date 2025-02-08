package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

data class Address(
    val id: Int,
    val userId: Int,
    val province: String,
    val city: String,
    val openAddress: String,
    val addressTel: String,
    val addressTitle: String
)
