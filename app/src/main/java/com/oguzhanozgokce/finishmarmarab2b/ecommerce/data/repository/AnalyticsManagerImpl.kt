package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AnalyticsManager
import javax.inject.Inject

class AnalyticsManagerImpl @Inject constructor(
    private val analytics: FirebaseAnalytics,
) : AnalyticsManager {
    override fun logCategoryViewed(categoryId: Int) {
        analytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, Bundle().apply {
            putInt(FirebaseAnalytics.Param.ITEM_ID, categoryId)
        }
        )
    }

    override fun logProductAddedToCart(productId: Int) {
        analytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, Bundle().apply {
            putInt(FirebaseAnalytics.Param.ITEM_ID, productId)
        }
        )
    }

    override fun logSearchQuery(searchQuery: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SEARCH, Bundle().apply {
            putString(FirebaseAnalytics.Param.SEARCH_TERM, searchQuery)
        }
        )
    }

    override fun logProductAddedToFavorites(productId: Int) {
        analytics.logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, Bundle().apply {
            putInt(FirebaseAnalytics.Param.ITEM_ID, productId)
        })
    }
}