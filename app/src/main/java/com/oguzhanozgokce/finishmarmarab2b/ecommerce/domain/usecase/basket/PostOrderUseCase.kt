package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.OrderRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostOrderUseCase @Inject constructor(
    private val repository: BasketRepository
) {
    suspend operator fun invoke(request: OrderRequest): Flow<Resource<Unit>> {
        return repository.postOrder(request)
    }
}
