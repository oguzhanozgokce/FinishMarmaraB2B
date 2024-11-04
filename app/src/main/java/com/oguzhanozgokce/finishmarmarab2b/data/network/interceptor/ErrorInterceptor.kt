package com.oguzhanozgokce.finishmarmarab2b.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.isSuccessful) {
            throw IOException("Unexpected response code: ${response.code}")
        }
        return response
    }
}