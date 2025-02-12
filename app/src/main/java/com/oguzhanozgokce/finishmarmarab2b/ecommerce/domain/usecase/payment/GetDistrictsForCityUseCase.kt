package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.CitiesRepository
import javax.inject.Inject

class GetDistrictsForCityUseCase @Inject constructor(
    private val repository: CitiesRepository
) {
    suspend operator fun invoke(cityName: String): Resource<List<String>> {
        return repository.getDistrictsForCity(cityName)
    }
}