package com.oguzhanozgokce.finishmarmarab2b.ecommerce.model

data class ProductUi(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val imageUrl: Int = 0,
    val description: String = "",
    val discount: Int = 0,
    val salePrice: String = "",
    val rating: Float = 1f,
    val sellerName: String = "",
) {
    fun getImageList(): List<Any?> {
        return listOf(imageUrl)
    }
}
