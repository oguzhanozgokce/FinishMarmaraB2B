package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product

import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orDoubleZero
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orZero
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toLocalDateTimeOrDefault
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.CategoryDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ImageDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.QuestionAnswerDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.SearchHistoryDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.SellerDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.UserCommentDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetBasketResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetFavoriteResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetSearchProductResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Image
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.SearchHistory
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Seller
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.UserComment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun ProductDto?.mapToProduct(): Product {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    return Product(
        id = this?.id.orZero(),
        title = this?.title.orEmpty(),
        description = this?.description.orEmpty(),
        price = this?.price.orDoubleZero(),
        discountedPrice = this?.discountedPrice.orDoubleZero(),
        percentageRate = this?.percentageRate.orEmpty(),
        sellerId = this?.sellerId.orZero(),
        stock = this?.stock.orZero(),
        rate = this?.rate.orDoubleZero(),
        commentCount = this?.commentCount.orZero(),
        favoriteCount = this?.favoriteCount.orZero(),
        basketCount = this?.basketCount.orZero(),
        categoryId = this?.categoryId.orZero(),
        isFavorite = this?.isFavorite ?: false,
        createdAt = this?.createdAt?.let {
            LocalDateTime.parse(it, formatter)
        } ?: LocalDateTime.now(),
        category = this?.category.toCategoryDomain(),
        seller = this?.seller.toSellerDomain(),
        images = this?.images?.map { it.toImageDomain() } ?: emptyList()
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
        percentageRate = this.percentageRate,
        sellerId = this.sellerId,
        stock = this.stock,
        rate = this.rate,
        commentCount = this.commentCount,
        favoriteCount = this.favoriteCount,
        basketCount = this.basketCount,
        categoryId = this.categoryId,
        isFavorite = this.isFavorite,
        createdAt = this.createdAt.format(formatter),
        category = this.category.toCategoryDto(),
        seller = this.seller.toSellerDto(),
        images = this.images.map { it.toImageDto() }
    )
}

fun GetFavoriteResponse.toProduct(): Product {
    return product.mapToProduct()
}

fun CategoryDto?.toCategoryDomain() = Category(
    id = this?.id.orZero(),
    name = this?.name.orEmpty(),
    categoryImage = this?.categoryImage.orEmpty()
)

fun Category.toCategoryDto() = CategoryDto(
    id = this.id,
    name = this.name,
    categoryImage = this.categoryImage
)

fun SellerDto?.toSellerDomain() = Seller(
    id = this?.id.orZero(),
    name = this?.name.orEmpty(),
    email = this?.email.orEmpty(),
    address = this?.address.orEmpty(),
    imageUrl = this?.imageUrl.orEmpty()
)

fun Seller.toSellerDto() = SellerDto(
    id = this.id,
    name = this.name,
    email = this.email,
    address = this.address,
    imageUrl = this.imageUrl
)

fun ImageDto?.toImageDomain() = Image(
    id = this?.id.orZero(),
    productId = this?.productId.orZero(),
    imageUrl = this?.imageUrl.orEmpty()
)

fun Image.toImageDto() = ImageDto(
    id = this.id,
    productId = this.productId,
    imageUrl = this.imageUrl
)

fun QuestionAnswerDto.toQuestionAnswerDomain() = QuestionAnswer(
    id = this.id.orZero(),
    question = this.question.orEmpty(),
    answer = this.answer.orEmpty(),
    date = this.date?.toLocalDateTimeOrDefault(
        formatter = DateTimeFormatter.ISO_DATE_TIME
    )?.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm")) ?: "",
    userName = this.userName.orEmpty(),
    sellerName = this.sellerName.orEmpty(),
    sellerImageUrl = this.sellerImageUrl.orEmpty()
)

fun UserCommentDto.toUserCommentDomain() = UserComment(
    id = this.id.orZero(),
    userName = this.userName.orEmpty(),
    date = this.date?.toLocalDateTimeOrDefault(
        formatter = DateTimeFormatter.ISO_DATE_TIME
    )?.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm")) ?: "",
    rating = this.rating.orDoubleZero(),
    comment = this.comment.orEmpty(),
    sellerName = this.sellerName.orEmpty()
)

fun List<GetBasketResponse>?.toProductList(): List<Product> {
    return this?.mapNotNull { basketItem ->
        basketItem.toProductOrNull()
    } ?: emptyList()
}

fun GetBasketResponse.toProductOrNull(): Product? {
    return product?.mapToProduct()?.copy(
        count = count.orZero()
    )
}

fun GetSearchProductResponse.toProductOrNull(): List<Product> {
    return list.orEmpty().map { productDto ->
        productDto.mapToProduct()
    }
}

fun SearchHistoryDto.toSearchHistoryDomain() = SearchHistory(
    id = this.id.orZero(),
    searchHistory = this.searchHistory.orEmpty()
)
