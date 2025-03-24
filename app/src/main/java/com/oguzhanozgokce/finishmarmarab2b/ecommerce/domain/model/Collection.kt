package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

data class Collection(
    val id: Int,
    val name: String,
    val imageList: List<String>,
    val productCount: Int
)
