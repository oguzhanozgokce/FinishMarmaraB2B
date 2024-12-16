package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun ProductDto.mapToProduct(): Product {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    return Product(
        productId = this.productId ?: 0,
        title = this.title.orEmpty(),
        description = this.description.orEmpty(),
        price = this.price ?: 0.0,
        sellerPrice = this.sellerPrice ?: 0.0,
        stock = this.stock ?: 0.0,
        rate = this.rate ?: 0.0,
        categoryId = this.categoryId ?: 0,
        createdTime = this.createdTime?.let { 
            LocalDateTime.parse(it, formatter)
        } ?: LocalDateTime.now(),
        commentId = this.commentId ?: 0,
        imageId = this.imageId ?: 0
    )
}

fun Product.mapToProductDto(): ProductDto {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    return ProductDto(
        productId = this.productId,
        title = this.title,
        description = this.description,
        price = this.price,
        sellerPrice = this.sellerPrice,
        stock = this.stock,
        rate = this.rate,
        categoryId = this.categoryId,
        createdTime = this.createdTime.format(formatter),
        commentId = this.commentId,
        imageId = this.imageId
    )
}

