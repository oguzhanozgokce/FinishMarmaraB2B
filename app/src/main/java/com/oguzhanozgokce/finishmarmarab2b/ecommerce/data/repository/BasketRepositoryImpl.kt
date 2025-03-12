package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orZero
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toResourceMap
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeApiCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toProductList
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostProductBasketRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.ApiService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.BasketRepository
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) : BasketRepository {

    private suspend fun getUserId(): Int {
        return localDataSource.getUserId() ?: -1
    }

    override suspend fun postProductBasket(productId: Int): Resource<Int> {
        val request = PostProductBasketRequest(
            productId = productId,
            userId = getUserId()
        )
        return safeApiCall { apiService.addProductToBasket(request) }.toResourceMap { response ->
            response.count.orZero()
        }
    }

    override suspend fun getBasketProducts(): Resource<List<Product>> {
        val userId = getUserId()
        return safeApiCall { apiService.getBasket(userId) }
            .toResourceMap { response ->
                response.list.toProductList()
            }
    }

    override suspend fun deleteBasketProduct(productId: Int): Resource<Unit> {
        val userId = getUserId()
        return safeApiCall { apiService.deleteBasket(userId, productId) }
    }

    override suspend fun deleteBasketAll(): Resource<Unit> {
        val userId = getUserId()
        return safeApiCall { apiService.deleteBasketAll(userId) }
    }
}
