package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.User
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.UserDto

fun User.mapToUserDomainModel(): UserDto {
    return UserDto(
        id = this.id.orEmpty(),
        email = this.email.orEmpty(),
        name = this.name.orEmpty(),
        surname = this.surname.orEmpty(),
        address = this.address.orEmpty()
    )
}
