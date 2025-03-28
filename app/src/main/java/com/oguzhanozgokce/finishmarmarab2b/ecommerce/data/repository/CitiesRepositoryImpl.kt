package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toResourceMap
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeApiCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.payment.mapToAddressList
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.payment.toDomainProvinces
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.payment.toSaveLocationRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SaveLocationRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.BasketService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Location
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Province
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.CitiesRepository
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val context: Context,
    private val basketService: BasketService,
    private val localDataSource: LocalDataSource
) : CitiesRepository {

    private suspend fun getUserId(): Int {
        return localDataSource.getUserId() ?: -1
    }

    private val cities: List<Province> by lazy { loadCitiesJson() }

    private fun loadCitiesJson(): List<Province> {
        try {
            val jsonString =
                context.assets.open("cities.json").bufferedReader().use { it.readText() }
            val citiesMap: Map<String, List<String>> =
                Gson().fromJson(jsonString, object : TypeToken<Map<String, List<String>>>() {}.type)
            return citiesMap.toDomainProvinces()
        } catch (e: Exception) {
            return emptyList()
        }
    }

    override suspend fun getCities(): Resource<List<Province>> = Resource.Success(cities)

    override suspend fun getDistrictsForCity(cityName: String): Resource<List<String>> {
        val city = cities.find { it.name.equals(cityName, ignoreCase = true) }
        return if (city != null) {
            Resource.Success(city.cities)
        } else {
            Resource.Error("City not found")
        }
    }

    override suspend fun getUserLocations(): Resource<List<Location>> {
        val userId = getUserId()
        return safeApiCall {
            basketService.getLocations(userId)
        }.toResourceMap { response ->
            response.mapToAddressList()
        }
    }

    override suspend fun postSaveLocation(address: SaveLocationRequest): Resource<Unit> {
        val userId = getUserId()
        val requestWithUserId = address.copy(userId = userId)
        return safeApiCall {
            basketService.saveAddress(requestWithUserId)
        }
    }

    override suspend fun putLocation(location: Location): Resource<Unit> {
        val userId = getUserId()
        val requestWithUserId = location.toSaveLocationRequest(userId)
        return safeApiCall {
            basketService.updateLocation(requestWithUserId)
        }
    }

    override suspend fun deleteLocation(locationId: Int): Resource<Unit> {
        return safeApiCall {
            basketService.deleteLocation(locationId)
        }
    }
}
