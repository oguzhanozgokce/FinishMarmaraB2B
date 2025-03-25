package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import javax.inject.Inject

class PostCollectionUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(collectionName: String): Resource<Int> {
        return repository.postCollection(collectionName)
    }
}
