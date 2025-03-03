package com.oguzhanozgokce.finishmarmarab2b.di

import android.content.Context
import com.oguzhanozgokce.finishmarmarab2b.core.domain.connectivity.ConnectivityListener
import com.oguzhanozgokce.finishmarmarab2b.core.domain.connectivity.ConnectivityListenerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {

    @Provides
    @Singleton
    fun provideConnectivityListener(
        @ApplicationContext context: Context,
    ): ConnectivityListener {
        return ConnectivityListenerImpl(context)
    }
}