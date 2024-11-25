package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val expirationTime: Long
)