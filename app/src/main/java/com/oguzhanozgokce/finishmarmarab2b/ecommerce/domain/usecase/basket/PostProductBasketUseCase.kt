package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.BasketRepository
import javax.inject.Inject

class PostProductBasketUseCase @Inject constructor(
    private val productRepository: BasketRepository
) {
    suspend operator fun invoke(productId: Int): Resource<Int> =
        productRepository.postProductBasket(productId)
}
