package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostCollectionAddProductsRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import javax.inject.Inject

class PostCollectionAddProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(collectionId: Int, productId: Int): Resource<Unit> {
        val request = PostCollectionAddProductsRequest(
            collectionId = collectionId,
            productId = productId
        )
        return repository.postCollectionAddProduct(request)
    }
}