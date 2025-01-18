package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product

import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orDoubleZero
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orZero
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.CategoryDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ImageDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.SellerDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Image
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Seller
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun ProductDto.mapToProduct(isFavorite: Boolean = false): Product {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    return Product(
        id = this.id.orZero(),
        title = this.title.orEmpty(),
        description = this.description.orEmpty(),
        price = this.price.orDoubleZero(),
        discountedPrice = this.discountedPrice.orDoubleZero(),
        sellerId = this.sellerId.orZero(),
        stock = this.stock.orZero(),
        rate = this.rate.orDoubleZero(),
        categoryId = this.categoryId.orZero(),
        createdAt = this.createdAt?.let {
            LocalDateTime.parse(it, formatter)
        } ?: LocalDateTime.now(),
        category = this.category?.toCategoryDomain(),
        seller = this.seller?.toSellerDomain(),
        images = this.images?.map { it.toImageDomain() },
        isFavorite = isFavorite
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
        seller = this.seller?.toSellerDto(),
        images = this.images?.map { it.toImageDto() }
    )
}

fun CategoryDto.toCategoryDomain() = Category(
    id = this.id.orZero(),
    name = this.name.orEmpty(),
    categoryImage = this.categoryImage.orEmpty()
)

fun Category.toCategoryDto() = CategoryDto(
    id = this.id,
    name = this.name,
    categoryImage = this.categoryImage
)

fun SellerDto.toSellerDomain() = Seller(
    id = this.id.orZero(),
    name = this.name.orEmpty()
)

fun Seller.toSellerDto() = SellerDto(
    id = this.id,
    name = this.name
)

fun ImageDto.toImageDomain() = Image(
    imageUrl = this.imageUrl.orEmpty()
)

fun Image.toImageDto() = ImageDto(
    imageUrl = this.imageUrl
)

