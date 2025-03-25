package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AnalyticsManager
import javax.inject.Inject

class AnalyticsManagerImpl @Inject constructor(
    private val analytics: FirebaseAnalytics,
) : AnalyticsManager {
    override fun logCategoryViewed(categoryId: Int) {
        analytics.logEvent(
            FirebaseAnalytics.Event.VIEW_ITEM_LIST,
            Bundle().apply {
                putInt(FirebaseAnalytics.Param.ITEM_ID, categoryId)
            }
        )
    }

    override fun logProductAddedToCart(productId: Int, productName: String) {
        analytics.logEvent(
            FirebaseAnalytics.Event.ADD_TO_CART,
            Bundle().apply {
                putInt(FirebaseAnalytics.Param.ITEM_ID, productId)
                putString(FirebaseAnalytics.Param.ITEM_NAME, productName)
            }
        )
    }

    override fun logSearchQuery(searchQuery: String) {
        analytics.logEvent(
            FirebaseAnalytics.Event.SEARCH,
            Bundle().apply {
                putString(FirebaseAnalytics.Param.SEARCH_TERM, searchQuery)
            }
        )
    }

    override fun logProductAddedToFavorites(productId: Int) {
        analytics.logEvent(
            FirebaseAnalytics.Event.ADD_TO_WISHLIST,
            Bundle().apply {
                putInt(FirebaseAnalytics.Param.ITEM_ID, productId)
            }
        )
    }

    override fun logCartViewed(productList: List<Product>) {
        val items = productList.map { product ->
            Bundle().apply {
                putString(FirebaseAnalytics.Param.ITEM_ID, product.id.toString())
                putString(FirebaseAnalytics.Param.ITEM_NAME, product.title)
                putDouble(FirebaseAnalytics.Param.PRICE, product.price)
                putInt(FirebaseAnalytics.Param.QUANTITY, product.count)
            }
        }
        val bundle = Bundle().apply {
            putParcelableArray(FirebaseAnalytics.Param.ITEMS, items.toTypedArray())
        }
        analytics.logEvent(FirebaseAnalytics.Event.VIEW_CART, bundle)
    }
}
