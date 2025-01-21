package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: Int): Resource<Product> {
        return productRepository.getProductDetail(productId)
    }
}