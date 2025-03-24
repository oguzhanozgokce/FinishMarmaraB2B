package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostCollectionAddProductsRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import javax.inject.Inject

class PostCollectionAddProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(request: List<PostCollectionAddProductsRequest>) =
        repository.postCollectionAddProducts(request)
}
