package com.oguzhanozgokce.finishmarmarab2b.di

import android.content.Context
import com.oguzhanozgokce.finishmarmarab2b.BuildConfig
import com.oguzhanozgokce.finishmarmarab2b.data.network.interceptor.ConnectivityInterceptor
import com.oguzhanozgokce.finishmarmarab2b.data.source.remote.MainService
import com.oguzhanozgokce.finishmarmarab2b.data.network.interceptor.AuthInterceptor
import com.oguzhanozgokce.finishmarmarab2b.data.network.interceptor.ErrorInterceptor
import com.oguzhanozgokce.finishmarmarab2b.domain.network.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.example.com/"

    @Provides
    @Singleton
    fun provideConnectivityInterceptor(
        @ApplicationContext context: Context
    ): ConnectivityInterceptor {
        return ConnectivityInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        authRepository: AuthRepository
    ): AuthInterceptor {
        return AuthInterceptor(authRepository)
    }

    @Provides
    @Singleton
    fun provideErrorInterceptor(): ErrorInterceptor {
        return ErrorInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        connectivityInterceptor: ConnectivityInterceptor,
        authInterceptor: AuthInterceptor,
        errorInterceptor: ErrorInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(authInterceptor)
            .addInterceptor(errorInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    fun provideApiService(retrofit: Retrofit): MainService {
        return retrofit.create(MainService::class.java)
    }
}