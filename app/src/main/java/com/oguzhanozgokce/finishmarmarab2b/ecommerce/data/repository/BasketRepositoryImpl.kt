package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orZero
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toResourceMap
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeApiCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.payment.mapToCreditCartList
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toProductList
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.CreditCartRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.OrderRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostProductBasketRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.BasketService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.BasketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val basketService: BasketService,
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
        return safeApiCall { basketService.addProductToBasket(request) }.toResourceMap { response ->
            response.count.orZero()
        }
    }

    override suspend fun getBasketProducts(): Resource<List<Product>> {
        val userId = getUserId()
        return safeApiCall { basketService.getBasket(userId) }
            .toResourceMap { response ->
                response.list.toProductList()
            }
    }

    override suspend fun deleteBasketProduct(productId: Int): Resource<Int> {
        val userId = getUserId()
        return safeApiCall { basketService.deleteBasket(userId, productId) }.toResourceMap {
            it.count.orZero()
        }
    }

    override suspend fun deleteBasketAll(): Resource<Unit> {
        val userId = getUserId()
        return safeApiCall { basketService.deleteBasketAll(userId) }
    }

    override suspend fun postOrder(request: OrderRequest): Flow<Resource<Unit>> = flow {
        val userId = getUserId()
        val response = safeApiCall { basketService.postOrder(request.copy(userId = userId)) }
        emit(response)
    }.flowOn(Dispatchers.IO)

    override suspend fun postCreditCart(request: CreditCartRequest): Resource<Unit> {
        val userId = getUserId()
        return safeApiCall { basketService.postCreditCard(request.copy(userId = userId)) }
    }

    override suspend fun getCreditCart(): Resource<List<CreditCart>> {
        val userId = getUserId()
        return safeApiCall { basketService.getCreditCard(userId) }.toResourceMap { response ->
            response.mapToCreditCartList()
        }
    }
}
