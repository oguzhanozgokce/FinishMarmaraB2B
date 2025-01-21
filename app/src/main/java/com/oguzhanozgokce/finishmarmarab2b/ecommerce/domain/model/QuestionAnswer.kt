package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

data class QuestionAnswer(
    val id: Int,
    val question: String,
    val answer: String,
    val date: String,
    val userName: String,
    val sellerName: String,
    val sellerImageUrl: String
)
