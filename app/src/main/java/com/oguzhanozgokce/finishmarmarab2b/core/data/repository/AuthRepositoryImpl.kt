package com.oguzhanozgokce.finishmarmarab2b.core.data.repository

import com.oguzhanozgokce.finishmarmarab2b.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.common.extension.map
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.user.mapToUser
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.ApiService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {

    override fun signIn(signInRequest: SignInRequest): Flow<Resource<User>>  = flow {
        emit(safeCall { apiService.signIn(signInRequest) }.map { it.user.mapToUser() })
    }
}