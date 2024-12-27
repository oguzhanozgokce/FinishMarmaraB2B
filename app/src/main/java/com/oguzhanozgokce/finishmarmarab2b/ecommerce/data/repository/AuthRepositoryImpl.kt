package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toResourceMap
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeApiCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.user.mapToUser
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.ApiService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.LoginResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.RegisterResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) : AuthRepository {

    override fun signIn(signInRequest: SignInRequest): Flow<Resource<LoginResponse>> = flow {
        val response = safeApiCall { apiService.signIn(signInRequest) }
        if (response is Resource.Success) {
            localDataSource.saveOrUpdateUserId(response.data.id)
        }
        emit(response)
    }

    override fun signUp(signUpRequest: SignUpRequest): Flow<Resource<RegisterResponse>> = flow {
        val response = safeApiCall { apiService.signUp(signUpRequest) }
        if (response is Resource.Success) {
            localDataSource.saveOrUpdateUserId(response.data.id)
        }
        emit(response)
    }.catch { e ->
        emit(Resource.Error("Unexpected error: ${e.localizedMessage}"))
    }


    override fun getUser(): Flow<Resource<User>> = flow {
        val userId = localDataSource.getUserId()
        if (userId == null) {
            emit(Resource.Error("User ID not found in local storage."))
            return@flow
        }
        val response = safeApiCall { apiService.getUser(userId) }
        val mappedResponse = response.toResourceMap { it.mapToUser() }
        emit(mappedResponse)
    }.catch { e ->
        emit(Resource.Error("Failed to fetch user data: ${e.localizedMessage}"))
    }

    override suspend fun saveOrUpdateEmail(email: String) {
        localDataSource.saveOrUpdateEmail(email)
    }

    override suspend fun getEmail(): String? {
        return localDataSource.getEmail()
    }
}