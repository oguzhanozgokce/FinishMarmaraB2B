package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Location
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.CitiesRepository
import javax.inject.Inject

class PutLocationUseCase @Inject constructor(
    private val repository: CitiesRepository
) {
    suspend operator fun invoke(location: Location) = repository.putLocation(location)
}