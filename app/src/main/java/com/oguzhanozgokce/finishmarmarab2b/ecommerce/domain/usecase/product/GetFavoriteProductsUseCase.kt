package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.core.common.Constant
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(
        limit: Int = Constant.PAGE_LIMIT,
        orderBy: String = Constant.ORDER_BY,
        sort: String = Constant.SHORT
    ): Flow<PagingData<Product>> {
        return repository.getFavoriteProducts(limit, orderBy, sort)
    }
}
