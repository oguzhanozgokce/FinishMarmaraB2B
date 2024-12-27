package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model

import java.time.LocalDate

data class User(
    val id: Int = 0,
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val birthDate: LocalDate = LocalDate.now(),
    val registerDate: LocalDate = LocalDate.now()
)

