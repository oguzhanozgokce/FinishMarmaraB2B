package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.BasketRepository
import javax.inject.Inject

class GetBasketProductsUseCase @Inject constructor(
    private val productRepository: BasketRepository
) {
    suspend operator fun invoke(): Resource<List<Product>> {
        return productRepository.getBasketProducts()
    }
}
