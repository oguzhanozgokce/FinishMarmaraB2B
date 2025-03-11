package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SaveLocationRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Location
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Province

interface CitiesRepository {
    suspend fun getCities(): Resource<List<Province>>
    suspend fun getDistrictsForCity(cityName: String): Resource<List<String>>
    suspend fun getUserLocations(): Resource<List<Location>>
    suspend fun postSaveLocation(address: SaveLocationRequest): Resource<Unit>
    suspend fun putLocation(location: Location): Resource<Unit>
    suspend fun deleteLocation(locationId: Int): Resource<Unit>
}
