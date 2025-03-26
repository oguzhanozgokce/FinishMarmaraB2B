package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AuthRepository
import javax.inject.Inject

class SaveOrUpdateEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String) = authRepository.saveOrUpdateEmail(email)
}
