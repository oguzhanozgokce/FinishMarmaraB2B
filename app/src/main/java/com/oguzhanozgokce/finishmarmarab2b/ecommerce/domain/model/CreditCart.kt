package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

data class CreditCart(
    val id: Int,
    val userId: Int,
    val cardTitle: String,
    val cardNumber: String,
    val lastDate: String,
    val cardCvv: String,
    val cardNameSurname: String,
    val cardType: CardType
)

enum class CardType(
    val displayName: String,
) {
    VISA("Visa"),
    MASTERCARD("Mastercard");

    companion object {
        fun find(value: String): CardType = entries.find { it.displayName == value } ?: VISA
    }
}
