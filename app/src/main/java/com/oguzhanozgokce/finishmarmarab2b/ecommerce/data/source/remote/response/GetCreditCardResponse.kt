package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.CreditCartDto

data class GetCreditCardResponse(
    @SerializedName("list")
    val list: List<CreditCartDto>? = null
)
