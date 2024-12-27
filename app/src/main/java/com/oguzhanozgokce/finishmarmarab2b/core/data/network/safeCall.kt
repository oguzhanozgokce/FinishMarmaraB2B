package com.oguzhanozgokce.finishmarmarab2b.core.data.network

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.FullResponse
import retrofit2.Response
import java.io.IOException

/**
 * // ApiResponseModel -> ApiResponse
 */

suspend fun <T> safeApiCall(
    execute: suspend () -> Response<FullResponse<T>>
): Resource<T> {
    return try {
        val response = execute()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null && body.status) {
                Resource.Success(body.data)
            } else {
                Resource.Error(body?.message ?: "Unknown error")
            }
        } else {
            Resource.Error("API call failed: ${response.message()} (Code: ${response.code()})")
        }
    } catch (e: IOException) {
        Resource.Error("Network error: ${e.localizedMessage}")
    } catch (e: Exception) {
        Resource.Error("Unexpected error: ${e.localizedMessage}")
    }
}




