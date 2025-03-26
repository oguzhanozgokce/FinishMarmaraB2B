package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import javax.inject.Inject

class DeleteCollectionUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(collectionId: Int) = repository.deleteCollection(collectionId)
}