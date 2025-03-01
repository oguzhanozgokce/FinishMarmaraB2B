package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class SaveLocationRequest(
    @SerializedName("user_id")
    val userId: Int? = null,
    @SerializedName("province")
    val province: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("open_address")
    val openAddress: String? = null,
    @SerializedName("address_tel")
    val addressTel: String? = null,
    @SerializedName("address_title")
    val addressTitle: String? = null,
    @SerializedName("location_name_surname")
    val nameSurname: String? = null,
)
