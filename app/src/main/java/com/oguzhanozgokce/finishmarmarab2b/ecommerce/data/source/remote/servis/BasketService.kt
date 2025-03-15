package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis

import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.DELETE_BASKET
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.DELETE_BASKET_ALL
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.DELETE_LOCATION
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_BASKET
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_CREDIT_CARD
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_LOCATIONS
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_BASKET
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_CREDIT_CARD
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_ORDER
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_SAVE_ADDRESS
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.PUT_LOCATION
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.CreditCartRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.OrderRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostProductBasketRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SaveLocationRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.BasketData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetCreditCardResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetLocationResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.PostBasketResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BasketService {
    @POST(POST_BASKET)
    suspend fun addProductToBasket(
        @Body request: PostProductBasketRequest,
    ): Response<ApiResponse<PostBasketResponse>>

    @GET(GET_BASKET)
    suspend fun getBasket(
        @Path("user_id") userId: Int,
    ): Response<ApiResponse<BasketData>>

    @DELETE(DELETE_BASKET)
    suspend fun deleteBasket(
        @Path("user_id") userId: Int,
        @Path("product_id") productId: Int,
    ): Response<ApiResponse<Unit>>

    @DELETE(DELETE_BASKET_ALL)
    suspend fun deleteBasketAll(
        @Path("user_id") userId: Int,
    ): Response<ApiResponse<Unit>>

    @POST(POST_ORDER)
    suspend fun postOrder(
        @Body request: OrderRequest,
    ): Response<ApiResponse<Unit>>

    @POST(POST_CREDIT_CARD)
    suspend fun postCreditCard(
        @Body request: CreditCartRequest,
    ): Response<ApiResponse<Unit>>

    @GET(GET_CREDIT_CARD)
    suspend fun getCreditCard(
        @Path("user_id") userId: Int,
    ): Response<ApiResponse<GetCreditCardResponse>>

    @PUT(PUT_LOCATION)
    suspend fun updateLocation(
        @Body request: SaveLocationRequest,
    ): Response<ApiResponse<Unit>>

    @DELETE(DELETE_LOCATION)
    suspend fun deleteLocation(
        @Path("location_id") locationId: Int,
    ): Response<ApiResponse<Unit>>

    @POST(POST_SAVE_ADDRESS)
    suspend fun saveAddress(
        @Body request: SaveLocationRequest,
    ): Response<ApiResponse<Unit>>

    @GET(GET_LOCATIONS)
    suspend fun getLocations(
        @Path("user_id") userId: Int,
    ): Response<ApiResponse<GetLocationResponse>>
}