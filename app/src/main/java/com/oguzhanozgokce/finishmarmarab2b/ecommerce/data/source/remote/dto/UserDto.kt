package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("surname")
    val surname: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("phone_Number")
    val phoneNumber: String? = "",
    @SerializedName("birth_Date")
    val birthDate: String? = "",
    @SerializedName("created_At")
    val createdAt: String? = ""
)


