package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.order

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.OrderRepository
import javax.inject.Inject

class GetOrderInfoUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke() = repository.getOrderInfo()
}
