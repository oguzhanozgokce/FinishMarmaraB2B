package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import javax.inject.Inject

class PutCollectionUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(collectionId: Int, name: String) =
        repository.putCollection(collectionId, name)
}
