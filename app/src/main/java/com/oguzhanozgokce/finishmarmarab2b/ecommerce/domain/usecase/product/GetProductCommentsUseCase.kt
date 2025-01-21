package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.UserComment
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductCommentsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(productId: Int): Flow<PagingData<UserComment>> {
        return repository.getProductComments(productId)
    }
}
