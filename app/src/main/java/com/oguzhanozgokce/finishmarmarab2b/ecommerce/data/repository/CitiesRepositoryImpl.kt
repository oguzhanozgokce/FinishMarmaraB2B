package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.payment.toDomainProvinces
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Province
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.CitiesRepository
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(private val context: Context) : CitiesRepository {

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
}