package com.oguzhanozgokce.finishmarmarab2b.core.data.network

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

suspend fun <T> safeApiCall(
    execute: suspend () -> Response<ApiResponse<T>>
): Resource<T> {
    return try {
        val response = execute()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null && body.status) {
                Resource.Success(body.data)
            } else {
                Resource.Error(body?.message ?: "Empty response body")
            }
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = extractErrorMessage(errorBody)
            Resource.Error(
                errorMessage ?: "API call failed: ${response.message()} (Code: ${response.code()})"
            )
        }
    } catch (e: IOException) {
        Resource.Error("Network error: ${e.localizedMessage}")
    } catch (e: SocketTimeoutException) {
        Resource.Error("", SocketTimeoutException("Timeout"))
    } catch (e: Exception) {
        Resource.Error("Unexpected error: ${e.localizedMessage}")
    }
}

fun extractErrorMessage(errorBody: String?): String? {
    return try {
        val jsonObject = JSONObject(errorBody ?: "")
        jsonObject.optString("message", null)
    } catch (e: JSONException) {
        null
    }
}
