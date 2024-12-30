package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.core.common.Constant.ORDER_BY
import com.oguzhanozgokce.finishmarmarab2b.core.common.Constant.PAGE_LIMIT
import com.oguzhanozgokce.finishmarmarab2b.core.common.Constant.SHORT
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(
        limit: Int = PAGE_LIMIT,
        orderBy: String = ORDER_BY,
        sort: String = SHORT
    ): Flow<PagingData<Product>>
}