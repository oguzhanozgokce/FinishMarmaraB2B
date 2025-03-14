package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.BasketRepository
import javax.inject.Inject

class GetCreditCartUseCase @Inject constructor(
    private val repository: BasketRepository
) {
    suspend operator fun invoke(): Resource<List<CreditCart>> = repository.getCreditCart()
}