package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Collection
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import javax.inject.Inject

class GetCollectionsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Resource<List<Collection>> = repository.getCollection()
}
