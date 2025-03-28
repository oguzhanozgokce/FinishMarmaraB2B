package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis

import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_ORDER_INFO
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetOrderInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderService {
    @GET(GET_ORDER_INFO)
    suspend fun getOrderInfo(
        @Path("userId") userId: Int
    ): Response<ApiResponse<GetOrderInfoResponse>>
}
