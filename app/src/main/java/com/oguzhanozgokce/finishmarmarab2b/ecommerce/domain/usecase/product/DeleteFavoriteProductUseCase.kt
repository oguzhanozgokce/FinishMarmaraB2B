package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onFailure
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.onSuccess
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.DeleteFavoriteProductRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.auth.GetUserIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFavoriteProductUseCase @Inject constructor(
    private val repository: ProductRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    operator fun invoke(productId: Int): Flow<Resource<Unit>> = flow {
        getUserIdUseCase()
            .first()
            .onSuccess { userId ->
                val request = DeleteFavoriteProductRequest(userId, productId)
                emitAll(repository.deleteFavoriteProduct(request))
            }
            .onFailure { emit(Resource.Error(it)) }
    }
}
