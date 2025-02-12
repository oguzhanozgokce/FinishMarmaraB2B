package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote

import com.google.gson.annotations.SerializedName

data class CitiesDTO(
    @SerializedName("cities")
    val cities: Map<String, List<String>>?
)