package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import kotlinx.coroutines.flow.Flow


/**
 * Commend
 */
interface AuthRepository {
    fun signIn(signInRequest: SignInRequest): Flow<Resource<Unit>>
    fun signUp(signUpRequest: SignUpRequest): Flow<Resource<Unit>>
}