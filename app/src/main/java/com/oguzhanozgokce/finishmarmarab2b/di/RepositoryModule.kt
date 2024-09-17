package com.oguzhanozgokce.finishmarmarab2b.di

import com.oguzhanozgokce.finishmarmarab2b.data.repository.MainRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMainRepository(repositoryImpl: MainRepositoryImpl): MainRepository
}