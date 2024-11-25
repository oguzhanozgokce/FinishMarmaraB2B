package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/refresh-token")
    suspend fun refreshToken(@Body refreshToken: String): Response<TokenResponse>
}