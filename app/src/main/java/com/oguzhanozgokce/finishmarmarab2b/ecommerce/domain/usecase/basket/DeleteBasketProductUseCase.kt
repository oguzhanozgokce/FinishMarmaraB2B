package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toResourceMap
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.BasketRepository
import javax.inject.Inject

class DeleteBasketProductUseCase @Inject constructor(
    private val productRepository: BasketRepository
) {
    suspend operator fun invoke(
        productId: Int,
        currentBasketProducts: List<Product>
    ): Resource<List<Product>> {
        return productRepository.deleteBasketProduct(productId).toResourceMap { newCount ->
            currentBasketProducts.filterNot { it.id == productId && newCount == 0 }
                .map { product ->
                    if (product.id == productId) {
                        product.copy(count = newCount)
                    } else {
                        product
                    }
                }
        }
    }
}
