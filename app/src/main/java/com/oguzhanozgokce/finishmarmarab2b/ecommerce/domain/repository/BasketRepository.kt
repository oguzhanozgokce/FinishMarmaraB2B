package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.CreditCartRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.OrderRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    suspend fun postProductBasket(productId: Int): Resource<Int>
    suspend fun getBasketProducts(): Resource<List<Product>>
    suspend fun deleteBasketProduct(productId: Int): Resource<Unit>
    suspend fun deleteBasketAll(): Resource<Unit>
    suspend fun postOrder(request: OrderRequest): Flow<Resource<Unit>>
    suspend fun postCreditCart(request: CreditCartRequest): Resource<Unit>
}
