package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.payment

import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orZero
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.AddressDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.CreditCartDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SaveLocationRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetLocationResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Address
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CardType
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Province

fun Address.toAddressDto(userId: Int): AddressDto {
    return AddressDto(
        id = this.id,
        userId = userId,
        province = this.province,
        city = this.city,
        openAddress = this.openAddress,
        addressTel = this.addressTel,
        addressTitle = this.addressTitle,
        nameSurname = this.nameSurname
    )
}

fun Address.toSaveLocationRequest(userId: Int): SaveLocationRequest {
    return SaveLocationRequest(
        userId = userId,
        province = this.province,
        city = this.city,
        openAddress = this.openAddress,
        addressTel = this.addressTel,
        addressTitle = this.addressTitle,
        nameSurname = this.nameSurname
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
        addressTitle = this.addressTitle.orEmpty(),
        nameSurname = this.nameSurname.orEmpty()
    )
}

fun GetLocationResponse.mapToAddressList(): List<Address> {
    return list.orEmpty().map { addressDto ->
        addressDto.mapToAddress()
    }
}

fun CreditCartDto.mapToCreditCart(): CreditCart {
    return CreditCart(
        id = this.id.orZero(),
        userId = this.userId.orZero(),
        cardTitle = this.cardTitle.orEmpty(),
        cardNumber = this.cardNumber.orEmpty(),
        lastDate = this.lastDate.orEmpty(),
        cardCvv = this.cardCvv.orEmpty(),
        cardNameSurname = this.cardNameSurname.orEmpty(),
        cardType = CardType.find(this.cardType.orEmpty())
    )
}

fun Map<String, List<String>>.toDomainProvinces(): List<Province> {
    return this.map { (provinceName, cities) ->
        Province(
            name = provinceName,
            cities = cities
        )
    }
}
