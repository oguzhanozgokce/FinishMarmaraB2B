package com.oguzhanozgokce.finishmarmarab2b.di

import android.content.SharedPreferences
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.interceptor.TokenInterceptor
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.token.TokenManager
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.token.TokenRepository
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.token.TokenRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TokenModule {

    @Provides
    @Singleton
    fun provideTokenManager(sharedPreferences: SharedPreferences): TokenManager {
        return TokenManager(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideTokenRepository(
        tokenManager: TokenManager,
        apiService: ApiService
    ): TokenRepository {
        return TokenRepositoryImpl(tokenManager, apiService)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenRepository: TokenRepository): TokenInterceptor {
        return TokenInterceptor(dagger.Lazy { tokenRepository })
    }
}
