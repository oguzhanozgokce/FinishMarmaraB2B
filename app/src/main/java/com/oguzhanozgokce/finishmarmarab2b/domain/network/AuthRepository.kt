package com.oguzhanozgokce.finishmarmarab2b.domain.network

interface AuthRepository {
    fun getToken(): String?
    fun refreshToken(): String?
    fun saveToken(token: String)
}
