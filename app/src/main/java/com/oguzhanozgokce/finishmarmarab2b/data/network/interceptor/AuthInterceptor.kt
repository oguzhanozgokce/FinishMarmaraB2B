package com.oguzhanozgokce.finishmarmarab2b.data.network.interceptor

import com.oguzhanozgokce.finishmarmarab2b.domain.network.AuthRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authRepository: AuthRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val token = authRepository.getToken()
        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        val response = chain.proceed(requestBuilder.build())
        if (response.code == 401) {
            val newToken = authRepository.refreshToken()
            if (!newToken.isNullOrEmpty()) {
                authRepository.saveToken(newToken)
                requestBuilder.header("Authorization", "Bearer $newToken")
                return chain.proceed(requestBuilder.build())
            }
        }
        return response
    }
}
