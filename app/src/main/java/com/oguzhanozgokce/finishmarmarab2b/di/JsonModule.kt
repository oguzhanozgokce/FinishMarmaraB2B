package com.oguzhanozgokce.finishmarmarab2b.di

import android.content.Context
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository.CitiesRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.CitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JsonModule {

    @Provides
    @Singleton
    fun provideCitiesJson(
        @ApplicationContext context: Context
    ): CitiesRepository {
        return CitiesRepositoryImpl(context)
    }
}