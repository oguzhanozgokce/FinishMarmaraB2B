package com.oguzhanozgokce.finishmarmarab2b.core.data.network.token

interface TokenRepository {
    suspend fun getRefreshToken(): String?
    suspend fun refreshAccessToken(): String?
    fun clearAllTokens()
}
