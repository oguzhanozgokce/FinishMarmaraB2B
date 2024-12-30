package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.CategoryDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.SellerDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Seller
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun ProductDto.mapToProduct(): Product {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    return Product(
        id = this.id ?: 0,
        title = this.title.orEmpty(),
        description = this.description.orEmpty(),
        price = this.price ?: 0.0,
        discountedPrice = this.discountedPrice ?: 0.0,
        sellerId = this.sellerId ?: 0,
        stock = this.stock ?: 0,
        rate = this.rate ?: 0.0,
        categoryId = this.categoryId ?: 0,
        createdAt = this.createdAt?.let {
            LocalDateTime.parse(it, formatter)
        } ?: LocalDateTime.now(),
        category = this.category?.totoCategoryDomain(),
        seller = this.seller?.toSellerDomain()
    )
}

fun Product.mapToProductDto(): ProductDto {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    return ProductDto(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        discountedPrice = this.discountedPrice,
        sellerId = this.sellerId,
        stock = this.stock,
        rate = this.rate,
        categoryId = this.categoryId,
        createdAt = this.createdAt.format(formatter),
        category = this.category?.toCategoryDto(),
        seller = this.seller?.toSellerDto()
    )
}

fun CategoryDto.totoCategoryDomain() = Category(
    id = this.id ?: 0,
    name = this.name.orEmpty(),
    categoryImage = this.categoryImage.orEmpty()
)

fun Category.toCategoryDto() = CategoryDto(
    id = this.id,
    name = this.name,
    categoryImage = this.categoryImage
)

fun SellerDto.toSellerDomain() = Seller(
    id = this.id ?: 0,
    name = this.name.orEmpty()
)

fun Seller.toSellerDto() = SellerDto(
    id = this.id,
    name = this.name
)
