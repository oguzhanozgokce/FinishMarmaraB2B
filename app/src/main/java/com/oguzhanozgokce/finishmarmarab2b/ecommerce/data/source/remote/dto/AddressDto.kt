package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class AddressDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("user_Id")
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
    @SerializedName("address_name")
    val addressName: String? = null,
    @SerializedName("address_surname")
    val addressSurname: String? = null
)
