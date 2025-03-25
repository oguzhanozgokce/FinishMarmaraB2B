package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AuthRepository
import javax.inject.Inject

class PutUserUseCase @Inject constructor(
    private val userRepository: AuthRepository
) {
    suspend operator fun invoke(
        name: String,
        surname: String,
        email: String,
        phoneNumber: String,
        birthDate: String
    ): Resource<Unit> {
        return userRepository.putUser(
            name = name,
            surname = surname,
            email = email,
            phoneNumber = phoneNumber,
            birthDate = birthDate
        )
    }
}
