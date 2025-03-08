package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.SearchRepository
import javax.inject.Inject

class GetSearchProductUseCase @Inject constructor(
    private val repository: SearchRepository,
) {
    suspend operator fun invoke(userId: Int, searchQuery: String): Resource<List<Product>> {
        return repository.getSearchProducts(userId, searchQuery)
    }
}