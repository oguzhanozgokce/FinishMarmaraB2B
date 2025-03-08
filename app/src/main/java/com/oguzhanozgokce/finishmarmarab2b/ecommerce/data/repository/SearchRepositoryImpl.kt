package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toResourceMap
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeApiCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toProductOrNull
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.ApiService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : SearchRepository {
    override suspend fun getSearchProducts(
        userId: Int,
        searchQuery: String,
    ): Resource<List<Product>> {
        return safeApiCall {
            apiService.getSearchProducts(userId, searchQuery)
        }.toResourceMap { response ->
            response.toProductOrNull()
        }
    }
}


