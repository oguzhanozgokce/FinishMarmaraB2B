package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.user

import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orEmpty
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orZero
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.UserDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun User.mapToUserDto(): UserDto {
    val formatter = DateTimeFormatter.ISO_DATE
    return UserDto(
        id = this.id,
        name = this.name,
        surname = this.surname,
        email = this.email,
        phoneNumber = this.phoneNumber,
        birthDate = this.birthDate.format(formatter),
        locationId = this.locationId,
        favoriteId = this.favoriteId,
        notificationId = this.notificationId,
        creditCardId = this.creditCardId,
        orderId = this.orderId,
        registerDate = this.registerDate.format(formatter)
    )
}

fun UserDto.mapToUser(): User {
    val formatter = DateTimeFormatter.ISO_DATE
    return User(
        id = this.id.orZero(),
        name = this.name.orEmpty(),
        surname = this.surname.orEmpty(),
        email = this.email.orEmpty(),
        phoneNumber = this.phoneNumber.orEmpty(),
        birthDate = this.birthDate
            ?.takeIf { it.isNotBlank() && it != "null" }
            ?.let { LocalDate.parse(it, formatter) }
            ?: LocalDate.now(),
        locationId = this.locationId.orZero(),
        favoriteId = this.favoriteId.orZero(),
        notificationId = this.notificationId.orZero(),
        creditCardId = this.creditCardId.orZero(),
        orderId = this.orderId.orZero(),
        registerDate = this.registerDate
            ?.takeIf { it.isNotBlank() && it != "null" }
            ?.let { LocalDate.parse(it, formatter) }
            ?: LocalDate.now()
    )
}