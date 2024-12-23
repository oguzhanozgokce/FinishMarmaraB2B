package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.SignInResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/signIn")
    suspend fun signIn(
        @Body request: SignInRequest
    ): ApiResponse<SignInResponse>

    @POST("/api/signUp")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): ApiResponse<SignUpResponse>
}