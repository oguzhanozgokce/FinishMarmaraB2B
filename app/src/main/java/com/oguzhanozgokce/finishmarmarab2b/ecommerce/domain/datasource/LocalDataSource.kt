package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource

interface LocalDataSource {
    suspend fun saveOrUpdateUserId(id: Int)
    suspend fun getUserId(): Int?
    suspend fun clearUserId()

    suspend fun saveOrUpdateEmail(email: String)
    suspend fun getEmail(): String?
    suspend fun clearEmail()
}
