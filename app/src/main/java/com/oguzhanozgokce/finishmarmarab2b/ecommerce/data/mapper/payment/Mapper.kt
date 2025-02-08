package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.payment

import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orZero
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.AddressDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.CreditCartDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Address
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart

fun Address.mapToAddressDto(): AddressDto {
    return AddressDto(
        id = this.id,
        userId = this.userId,
        province = this.province,
        city = this.city,
        openAddress = this.openAddress,
        addressTel = this.addressTel,
        addressTitle = this.addressTitle
    )
}

fun AddressDto.mapToAddress(): Address {
    return Address(
        id = this.id.orZero(),
        userId = this.userId.orZero(),
        province = this.province.orEmpty(),
        city = this.city.orEmpty(),
        openAddress = this.openAddress.orEmpty(),
        addressTel = this.addressTel.orEmpty(),
        addressTitle = this.addressTitle.orEmpty()
    )
}

fun CreditCart.mapToCreditCartDto(): CreditCartDto {
    return CreditCartDto(
        id = this.id,
        userId = this.userId,
        cardNumber = this.cardNumber,
        lastDate = this.lastDate,
        cardCvv = this.cardCvv,
        cardNameSurname = this.cardNameSurname,
        cardType = this.cardType
    )
}

fun CreditCartDto.mapToCreditCart(): CreditCart {
    return CreditCart(
        id = this.id.orZero(),
        userId = this.userId.orZero(),
        cardNumber = this.cardNumber.orEmpty(),
        lastDate = this.lastDate.orEmpty(),
        cardCvv = this.cardCvv.orEmpty(),
        cardNameSurname = this.cardNameSurname.orEmpty(),
        cardType = this.cardType.orEmpty()
    )
}