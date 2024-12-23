package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toMap
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.ApiService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {

    override fun signIn(signInRequest: SignInRequest): Flow<Resource<Unit>> = flow {
        emit(safeCall { apiService.signIn(signInRequest) }.toMap { })
    }

    override fun signUp(signUpRequest: SignUpRequest): Flow<Resource<Unit>> = flow {
        emit(safeCall { apiService.signUp(signUpRequest) }.toMap { })
    }
}