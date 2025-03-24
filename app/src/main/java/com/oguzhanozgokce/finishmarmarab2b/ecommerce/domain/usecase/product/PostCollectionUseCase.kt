package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostCollectionRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import javax.inject.Inject

class PostCollectionUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(request: PostCollectionRequest) = repository.postCollection(request)
}
