package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.user

import android.util.Log
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orEmpty
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orZero
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toLocalDateOrDefault
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.UserDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetUserResponse
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
        createdAt = this.registerDate.format(formatter)
    )
}

fun UserDto.mapToDomainUser(): User {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    return User(
        id = this.id.orZero(),
        name = this.name.orEmpty(),
        surname = this.surname.orEmpty(),
        email = this.email.orEmpty(),
        phoneNumber = this.phoneNumber.orEmpty(),
        birthDate = this.birthDate.toLocalDateOrDefault(formatter),
        registerDate = this.createdAt.toLocalDateOrDefault(formatter)
    )
}

fun GetUserResponse.mapToUser(): User {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    return User(
        id = this.id.orZero(),
        name = this.name.orEmpty(),
        surname = this.surname.orEmpty(),
        email = this.email.orEmpty(),
        phoneNumber = this.phoneNumber.orEmpty(),
        birthDate = this.birthDate.toLocalDateOrDefault(formatter),
        registerDate = this.createdAt.toLocalDateOrDefault(formatter)
    )
}

