package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request

data class SignUpRequest(
    val id : Int,
    val name : String,
    val surname : String,
    val email : String,
    val password : String,
    val phoneNumber : String
)