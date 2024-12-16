package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.GET


interface ApiService {
    @GET("/api/signIn")
    suspend fun signIn(
        @Body request: SignInRequest
    ): ApiResponse<SignInResponse>
}