package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

data class SearchHistory(
    val id: Int,
    val userId: Int,
    val searchHistory: String,
    val createdAt: String
)
