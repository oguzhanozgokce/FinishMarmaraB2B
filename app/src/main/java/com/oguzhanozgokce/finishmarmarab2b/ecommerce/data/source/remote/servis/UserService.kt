package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis

import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_USER
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.LOGIN
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.REGISTER
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetUserResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.LoginResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST(LOGIN)
    suspend fun signIn(
        @Body request: SignInRequest,
    ): Response<ApiResponse<LoginResponse>>

    @POST(REGISTER)
    suspend fun signUp(
        @Body request: SignUpRequest,
    ): Response<ApiResponse<RegisterResponse>>

    @GET(GET_USER)
    suspend fun getUser(
        @Path("id") id: Int,
    ): Response<ApiResponse<GetUserResponse>>
}