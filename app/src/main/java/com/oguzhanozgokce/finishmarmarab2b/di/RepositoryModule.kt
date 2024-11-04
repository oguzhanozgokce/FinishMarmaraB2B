package com.oguzhanozgokce.finishmarmarab2b.di

import com.oguzhanozgokce.finishmarmarab2b.data.repository.MainRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.data.repository.UserRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.domain.repository.MainRepository
import com.oguzhanozgokce.finishmarmarab2b.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMainRepository(repositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun bindUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository
}