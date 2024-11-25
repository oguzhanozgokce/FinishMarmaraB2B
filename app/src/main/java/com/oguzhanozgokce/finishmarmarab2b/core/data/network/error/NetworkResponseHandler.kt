package com.oguzhanozgokce.finishmarmarab2b.core.data.network.error

import com.oguzhanozgokce.finishmarmarab2b.common.Resource
import retrofit2.Response

fun <T> responseToResult(response: Response<T>): Resource<T> {
    return if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            Resource.Success(body)
        } else {
            Resource.Error("Response body is null.")
        }
    } else {
        when (response.code()) {
            408 -> Resource.Error("Request timeout.")
            429 -> Resource.Error("Too many requests.")
            in 500..599 -> Resource.Error("Server error.")
            else -> Resource.Error("Unknown error with code ${response.code()}.")
        }
    }
}
