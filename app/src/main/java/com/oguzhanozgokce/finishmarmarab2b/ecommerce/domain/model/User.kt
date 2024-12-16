package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

import java.time.LocalDate

data class User(
    val id: Int = 0,
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val birthDate: LocalDate =  LocalDate.now(),
    val locationId: Int = 0,
    val favoriteId: Int = 0,
    val notificationId: Int = 0,
    val creditCardId: Int = 0,
    val orderId: Int = 0,
    val registerDate: LocalDate = LocalDate.now()
)
