package com.oguzhanozgokce.finishmarmarab2b.data.mapper

import com.oguzhanozgokce.finishmarmarab2b.data.source.remote.model.User
import com.oguzhanozgokce.finishmarmarab2b.domain.model.UserDomain

fun User.mapToUserDomainModel(): UserDomain {
    return UserDomain(
        id = this.id.orEmpty(),
        email = this.email.orEmpty(),
        name = this.name.orEmpty(),
        surname = this.surname.orEmpty(),
        address = this.address.orEmpty()
    )
}
