package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toResourceMap
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeApiCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.order.mapToOrderInfoList
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.OrderService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.OrderInfo
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val apiService: OrderService,
    private val localDataSource: LocalDataSource
) : OrderRepository {

    private suspend fun getUserId(): Int {
        return localDataSource.getUserId() ?: -1
    }

    override suspend fun getOrderInfo(): Resource<List<OrderInfo>> {
        val userId = getUserId()
        return safeApiCall { apiService.getOrderInfo(userId) }.toResourceMap { response ->
            response.mapToOrderInfoList()
        }
    }
}
