package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request

data class AddFavoriteProductRequest(
    val userId: Int,
    val productId: Int
)
