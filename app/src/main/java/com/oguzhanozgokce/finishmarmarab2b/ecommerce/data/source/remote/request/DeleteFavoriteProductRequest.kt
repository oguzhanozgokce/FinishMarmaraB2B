package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request

data class DeleteFavoriteProductRequest(
    val userId: Int,
    val productId: Int
)
