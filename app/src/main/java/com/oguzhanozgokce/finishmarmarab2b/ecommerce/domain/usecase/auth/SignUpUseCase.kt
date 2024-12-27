package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth


import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.capitalizeFirstLetter
import com.oguzhanozgokce.finishmarmarab2b.core.common.validation.InputValidator
import com.oguzhanozgokce.finishmarmarab2b.core.common.validation.ValidationResult
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.RegisterResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(signUpRequest: SignUpRequest): Flow<Resource<RegisterResponse>> = flow {
        val cleanedRequest = signUpRequest.copy(
            email = signUpRequest.email.trim(),
            phoneNumber = signUpRequest.phoneNumber.trim(),
            password = signUpRequest.password.trim(),
            name = signUpRequest.name.trim().capitalizeFirstLetter(),
            surname = signUpRequest.surname.trim().capitalizeFirstLetter()
        )

        if (!validateAndEmit(InputValidator.validateEmail(cleanedRequest.email))) return@flow
        if (!validateAndEmit(InputValidator.validatePhoneNumber(cleanedRequest.phoneNumber))) return@flow
        if (!validateAndEmit(InputValidator.validatePassword(cleanedRequest.password))) return@flow
        if (!validateAndEmit(InputValidator.validateName(cleanedRequest.name))) return@flow
        if (!validateAndEmit(InputValidator.validateSurname(cleanedRequest.surname))) return@flow

        emitAll(authRepository.signUp(cleanedRequest))
    }

    private suspend fun FlowCollector<Resource<RegisterResponse>>.validateAndEmit(
        validation: ValidationResult
    ): Boolean {
        if (validation is ValidationResult.Error) {
            emit(Resource.Error(validation.message))
            return false
        }
        return true
    }
}
