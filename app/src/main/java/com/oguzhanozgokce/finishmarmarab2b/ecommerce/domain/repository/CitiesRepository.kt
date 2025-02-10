package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.City

interface CitiesRepository {
    suspend fun getCities(): Resource<List<City>>
    suspend fun getDistrictsForCity(cityName: String): Resource<List<String>>
}
