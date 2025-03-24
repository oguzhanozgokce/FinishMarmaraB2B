package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class PostCollectionAddProductsRequest(
    @SerializedName("collection_id")
    val collectionId: Int,
    @SerializedName("product_id")
    val productId: Int
)
