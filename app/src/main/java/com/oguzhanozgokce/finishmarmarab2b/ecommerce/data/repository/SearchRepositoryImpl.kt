package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toResourceMap
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeApiCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toProductOrNull
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.ProductService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val localDataSource: LocalDataSource
) : SearchRepository {
    private suspend fun getUserId(): Int {
        return localDataSource.getUserId() ?: -1
    }

    override suspend fun getSearchProducts(
        searchQuery: String,
    ): Resource<List<Product>> {
        return safeApiCall {
            productService.getSearchProducts(getUserId(), searchQuery)
        }.toResourceMap { response ->
            response.toProductOrNull()
        }
    }
}
