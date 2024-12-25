package com.oguzhanozgokce.finishmarmarab2b.core.data.network

import android.util.Log
import com.google.gson.JsonParseException
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
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
        Log.e("safeCall", "IOException: ${e.localizedMessage}")
        Resource.Error("No internet connection.")
    } catch (e: JsonParseException) {
        Resource.Error("Serialization error: ${e.localizedMessage}")
    } catch (e: Exception) {
        Resource.Error("An unknown error occurred.")
    }
}
