package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment// package: com.example.myapp.domain.usecase

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.City
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.CitiesRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: CitiesRepository
) {
    suspend operator fun invoke(): Resource<List<City>> = repository.getCities()
}
