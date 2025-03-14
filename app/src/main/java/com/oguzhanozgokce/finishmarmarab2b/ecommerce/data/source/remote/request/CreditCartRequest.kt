package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class CreditCartRequest(
    @SerializedName("user_Id")
    val userId: Int? = null,
    @SerializedName("card_Number")
    val cardNumber: String? = null,
    @SerializedName("last_Date")
    val lastDate: String? = null,
    @SerializedName("card_Ccv")
    val cardCvv: String? = null,
    @SerializedName("card_Name_Surname")
    val cardNameSurname: String? = null,
    @SerializedName("card_Title")
    val cardTitle: String? = null,
)
