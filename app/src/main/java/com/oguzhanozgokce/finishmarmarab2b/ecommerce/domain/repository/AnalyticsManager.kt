package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product

interface AnalyticsManager {
    fun logCategoryViewed(categoryId: Int)
    fun logProductAddedToCart(productId: Int)
    fun logSearchQuery(searchQuery: String)
    fun logProductAddedToFavorites(productId: Int)
    fun logCartViewed(productList: List<Product>)
}
