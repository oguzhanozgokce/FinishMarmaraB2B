package com.oguzhanozgokce.finishmarmarab2b.core.data.network.token

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.ApiService
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenManager: TokenManager,
    private val apiService: ApiService
) : TokenRepository {



    override suspend fun getRefreshToken(): String? {
        return tokenManager.getRefreshToken()
    }

    override suspend fun refreshAccessToken(): String? {
        TODO()
    }

    override fun clearAllTokens() {
        tokenManager.clearAllTokens()
    }
}
