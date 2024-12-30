package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(
    ): Flow<PagingData<Product>> {
        return repository.getProducts()
    }
}
