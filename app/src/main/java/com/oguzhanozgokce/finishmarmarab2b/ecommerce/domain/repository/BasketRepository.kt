package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product

interface BasketRepository {
    suspend fun postProductBasket(productId: Int): Resource<Int>
    suspend fun getBasketProducts(): Resource<List<Product>>
    suspend fun deleteBasketProduct(productId: Int): Resource<Unit>
    suspend fun deleteBasketAll(): Resource<Unit>
}