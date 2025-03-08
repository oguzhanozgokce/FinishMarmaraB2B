package com.oguzhanozgokce.finishmarmarab2b.di

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository.AuthRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository.ProductRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository.SearchRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AuthRepository
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.SearchRepository
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
    abstract fun bindUserRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindSearchRepository(repositoryImpl: SearchRepositoryImpl): SearchRepository
}
