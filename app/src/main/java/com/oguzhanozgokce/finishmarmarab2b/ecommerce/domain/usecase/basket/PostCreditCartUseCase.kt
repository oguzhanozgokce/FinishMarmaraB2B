package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.CreditCartRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.BasketRepository
import javax.inject.Inject

class PostCreditCartUseCase @Inject constructor(
    private val repository: BasketRepository
) {
    suspend operator fun invoke(request: CreditCartRequest) = repository.postCreditCart(request)
}