package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("user_Id")
    val userId: Int? = null,
    @SerializedName("province")
    val province: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("open_Address")
    val openAddress: String? = null,
    @SerializedName("address_Tel")
    val addressTel: String? = null,
    @SerializedName("address_Title")
    val addressTitle: String? = null,
    @SerializedName("location_name_surname")
    val nameSurname: String? = null,
)
