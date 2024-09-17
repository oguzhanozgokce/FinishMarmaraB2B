package com.oguzhanozgokce.finishmarmarab2b.data.repository

import com.oguzhanozgokce.finishmarmarab2b.data.source.remote.MainService
import com.oguzhanozgokce.finishmarmarab2b.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
) : MainRepository