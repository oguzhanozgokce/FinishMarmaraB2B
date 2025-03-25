package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class PutUserRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone_Number")
    val phoneNumber: String,
    @SerializedName("birth_Date")
    val birthDate: String,
)
