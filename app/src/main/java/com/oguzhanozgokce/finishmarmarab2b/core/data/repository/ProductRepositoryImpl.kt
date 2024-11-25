package com.oguzhanozgokce.finishmarmarab2b.core.data.repository

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.ApiService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ProductRepository