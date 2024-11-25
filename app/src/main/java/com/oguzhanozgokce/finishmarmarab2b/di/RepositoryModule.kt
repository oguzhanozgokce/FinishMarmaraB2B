package com.oguzhanozgokce.finishmarmarab2b.di

import com.oguzhanozgokce.finishmarmarab2b.core.data.repository.ProductRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.core.data.repository.UserRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMainRepository(repositoryImpl: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun bindUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository
}