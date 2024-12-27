package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.validation.InputValidator
import com.oguzhanozgokce.finishmarmarab2b.core.common.validation.ValidationResult
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.LoginResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(signInRequest: SignInRequest): Flow<Resource<LoginResponse>> = flow{

        val emailValidation = InputValidator.validateEmail(signInRequest.email)
        if (emailValidation is ValidationResult.Error) {
            emit(Resource.Error(emailValidation.message))
            return@flow
        }
        if (signInRequest.password.isEmpty()) {
            emit(Resource.Error("Password cannot be empty."))
            return@flow
        }

        val validationSignInRequest = signInRequest.copy(
            email = signInRequest.email.trim(),
            password = signInRequest.password.trim()
        )
        emitAll(authRepository.signIn(validationSignInRequest))
    }
}
