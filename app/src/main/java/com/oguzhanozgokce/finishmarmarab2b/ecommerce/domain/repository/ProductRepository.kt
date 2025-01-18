package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.core.common.Constant.ORDER_BY
import com.oguzhanozgokce.finishmarmarab2b.core.common.Constant.PAGE_LIMIT
import com.oguzhanozgokce.finishmarmarab2b.core.common.Constant.SHORT
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.AddFavoriteProductRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.DeleteFavoriteProductRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.ToggleFavoriteRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(
        limit: Int = PAGE_LIMIT,
        orderBy: String = ORDER_BY,
        sort: String = SHORT
    ): Flow<PagingData<Product>>

    fun getFavoriteProducts(limit: Int, orderBy: String, sort: String): Flow<PagingData<Product>>


    fun addProductToFavorites(addFavoriteProductRequest: AddFavoriteProductRequest): Flow<Resource<Unit>>
    fun toggleFavorite(toggleFavoriteRequest: ToggleFavoriteRequest): Flow<Resource<Unit>>
    fun deleteFavoriteProduct(deleteFavoriteProductRequest: DeleteFavoriteProductRequest): Flow<Resource<Unit>>
}