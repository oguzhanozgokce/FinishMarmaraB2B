package com.oguzhanozgokce.finishmarmarab2b.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository.AnalyticsManagerImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AnalyticsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Provides
    @Singleton
    fun provideFirebaseAnalytics(@ApplicationContext context: Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideAnalyticsManager(
        firebaseAnalytics: FirebaseAnalytics
    ): AnalyticsManager {
        return AnalyticsManagerImpl(firebaseAnalytics)
    }
}
