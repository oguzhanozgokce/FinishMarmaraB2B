package com.oguzhanozgokce.finishmarmarab2b.core.data.repository

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.ApiService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
}