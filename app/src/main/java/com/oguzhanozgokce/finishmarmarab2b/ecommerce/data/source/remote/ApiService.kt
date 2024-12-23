package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/signIn")
    suspend fun signIn(
        @Body request: SignInRequest
    ): ApiResponse<Unit>

    @POST("/api/signUp")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): ApiResponse<Unit>
}