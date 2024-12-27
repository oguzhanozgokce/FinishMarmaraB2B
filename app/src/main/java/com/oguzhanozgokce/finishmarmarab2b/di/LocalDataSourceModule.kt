package com.oguzhanozgokce.finishmarmarab2b.di

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.datasource.LocalDataSourceImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource
}
