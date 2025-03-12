package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.LocationDto

data class GetLocationResponse(
    @SerializedName("list")
    val list: List<LocationDto>? = null
)
