package com.oguzhanozgokce.finishmarmarab2b.data.repository

import com.oguzhanozgokce.finishmarmarab2b.data.source.remote.MainService
import com.oguzhanozgokce.finishmarmarab2b.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val mainService: MainService) : UserRepository {
}