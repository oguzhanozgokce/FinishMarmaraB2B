package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

data class CreditCart(
    val id: Int,
    val userId: Int,
    val cardNumber: String,
    val lastDate: String,
    val cardCvv: String,
    val cardNameSurname: String,
    val cardType: String
)
