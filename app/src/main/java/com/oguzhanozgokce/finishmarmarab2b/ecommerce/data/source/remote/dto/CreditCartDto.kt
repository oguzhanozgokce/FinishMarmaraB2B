package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class CreditCartDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("user_Id")
    val userId: Int? = null,
    @SerializedName("card_number")
    val cardNumber: String? = null,
    @SerializedName("last_date")
    val lastDate: String? = null,
    @SerializedName("card_ccv")
    val cardCvv: String? = null,
    @SerializedName("card_name_surname")
    val cardNameSurname: String? = null,
    @SerializedName("card_title")
    val cardTitle: String? = null,
    @SerializedName("card_type")
    val cardType: String? = null
)

