package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Address
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.CitiesRepository
import javax.inject.Inject

class GetUserLocationsUseCase @Inject constructor(
    private val citiesRepository: CitiesRepository
) {
    suspend operator fun invoke(): Resource<List<Address>> {
        return citiesRepository.getUserLocations()
    }
}
