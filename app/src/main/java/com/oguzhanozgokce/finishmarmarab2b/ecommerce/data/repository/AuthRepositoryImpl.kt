package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toResourceMap
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeApiCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.user.mapToUser
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PutUserRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.LoginResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.RegisterResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.UserService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val localDataSource: LocalDataSource
) : AuthRepository {

    private suspend fun getUserId(): Int {
        return localDataSource.getUserId() ?: -1
    }

    override fun signIn(request: SignInRequest): Flow<Resource<LoginResponse>> = flow {
        val response = safeApiCall { userService.signIn(request) }
        if (response is Resource.Success) {
            localDataSource.saveOrUpdateUserId(response.data.id)
        }
        emit(response)
    }

    override fun signUp(request: SignUpRequest): Flow<Resource<RegisterResponse>> = flow {
        val response = safeApiCall { userService.signUp(request) }
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
        val response = safeApiCall { userService.getUser(userId) }
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

    override suspend fun putUser(
        name: String,
        surname: String,
        email: String,
        phoneNumber: String,
        birthDate: String
    ): Resource<Unit> {
        val userId = getUserId()
        val request = PutUserRequest(
            id = userId,
            name = name,
            surname = surname,
            email = email,
            phoneNumber = phoneNumber,
            birthDate = birthDate
        )
        return safeApiCall { userService.putUser(request) }
    }
}
