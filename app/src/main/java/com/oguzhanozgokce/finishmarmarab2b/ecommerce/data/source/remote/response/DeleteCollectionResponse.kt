package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DeleteCollectionResponse(
    @SerializedName("collection_id")
    val collectionId: Int
)
