package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AuthRepository
import javax.inject.Inject

class GetEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): String? {
        return authRepository.getEmail()
    }
}
