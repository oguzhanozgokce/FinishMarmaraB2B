package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {
    operator fun invoke(): Flow<PagingData<Category>> {
        return productRepository.getCategories()
    }
}
