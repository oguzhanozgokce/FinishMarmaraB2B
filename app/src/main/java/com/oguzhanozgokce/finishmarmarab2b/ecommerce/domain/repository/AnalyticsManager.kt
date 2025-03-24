package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository

interface AnalyticsManager {
    fun logCategoryViewed(categoryId: Int)
    fun logProductAddedToCart(productId: Int)
    fun logSearchQuery(searchQuery: String)
    fun logProductAddedToFavorites(productId: Int)
}
