package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import android.content.Context
import com.google.gson.Gson
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.payment.toDomainCities
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.CitiesDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.City
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.CitiesRepository

class CitiesRepositoryImpl(private val context: Context) : CitiesRepository {

    private val cities: List<City> by lazy { loadCitiesJson() }

    private fun loadCitiesJson(): List<City> {
        val jsonString = context.assets.open("cities.json").bufferedReader().use { it.readText() }
        val citiesDto: CitiesDto = Gson().fromJson(jsonString, CitiesDto::class.java)
        return citiesDto.toDomainCities()
    }

    override suspend fun getCities(): Resource<List<City>> = Resource.Success(cities)

    override suspend fun getDistrictsForCity(cityName: String): Resource<List<String>> {
        val city = cities.find { it.cityName.equals(cityName, ignoreCase = true) }
        return if (city != null) {
            Resource.Success(city.districts)
        } else {
            Resource.Error("City not found")
        }
    }
}