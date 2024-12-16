package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("user_id")
    val id: Int? = 0,
    @SerializedName("user_name")
    val name: String? = "",
    @SerializedName("user_surname")
    val surname: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("password")
    val password: String? = "",
    @SerializedName("phone_number")
    val phoneNumber: String? = "",
    @SerializedName("birth_date")
    val birthDate: String? = "null", // ISO 8601 formatında gelir
    @SerializedName("location_id")
    val locationId: Int? = 0,
    @SerializedName("favorite_id")
    val favoriteId: Int? = 0,
    @SerializedName("notification_id")
    val notificationId: Int? = 0,
    @SerializedName("credit_card_id")
    val creditCardId: Int? = 0,
    @SerializedName("order_id")
    val orderId: Int? = 0,
    @SerializedName("register_date")
    val registerDate: String? = ""
)
