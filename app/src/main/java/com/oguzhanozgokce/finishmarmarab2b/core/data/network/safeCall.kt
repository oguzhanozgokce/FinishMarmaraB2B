package com.oguzhanozgokce.finishmarmarab2b.core.data.network

import com.google.gson.JsonParseException
import com.oguzhanozgokce.finishmarmarab2b.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.error.responseToResult
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import retrofit2.Response
import java.io.IOException

/**
 * Safely executes an API call and converts the result to a Resource<T>.
 * - Returns Resource.Success if the call succeeds.
 * - Catches and handles exceptions, returning appropriate Resource.Error messages.
 */
suspend inline fun <T> safeCall(
    crossinline execute: suspend () -> ApiResponse<T>
): Resource<T> {
    return try {
        val response = execute()
        responseToResult(response)
    } catch (e: IOException) {
        Resource.Error("No internet connection.")
    } catch (e: JsonParseException) {
        Resource.Error("Serialization error.")
    } catch (e: Exception) {
        Resource.Error("An unknown error occurred.")
    }
}
