package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val id: Int,
    val userId: Int,
    val province: String,
    val city: String,
    val openAddress: String,
    val addressTel: String,
    val addressTitle: String,
    val nameSurname: String
)
