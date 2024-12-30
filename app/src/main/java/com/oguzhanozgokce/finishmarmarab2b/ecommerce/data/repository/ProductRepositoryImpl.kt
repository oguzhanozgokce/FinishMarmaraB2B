package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.mapToProduct
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.ApiService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.paging.ProductPagingSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ProductRepository {
    override fun getProducts(
        limit: Int,
        orderBy: String,
        sort: String
    ): Flow<PagingData<Product>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = limit,
                initialLoadSize = limit,
                prefetchDistance = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ProductPagingSource(
                    apiService = apiService,
                    limit = limit,
                    orderBy = orderBy,
                    sort = sort
                )
            }
        )
        return pager.flow
            .map { pagingData: PagingData<ProductDto> ->
                pagingData.map { dto -> dto.mapToProduct() }
            }
    }
}