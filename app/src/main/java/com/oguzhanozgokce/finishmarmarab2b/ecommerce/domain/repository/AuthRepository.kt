package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.DeleteFavoriteProductRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.ToggleFavoriteRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.LoginResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.RegisterResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signIn(signInRequest: SignInRequest): Flow<Resource<LoginResponse>>
    fun signUp(signUpRequest: SignUpRequest): Flow<Resource<RegisterResponse>>
    fun getUser(): Flow<Resource<User>>

    suspend fun saveOrUpdateEmail(email: String)
    suspend fun getEmail(): String?

    fun getUserId(): Flow<Resource<Int>>
}