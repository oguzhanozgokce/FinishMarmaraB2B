package com.oguzhanozgokce.finishmarmarab2b.core.data.network

import com.google.gson.JsonParseException
import com.oguzhanozgokce.finishmarmarab2b.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.error.responseToResult
import retrofit2.Response
import java.io.IOException

suspend inline fun <T> safeCall(
    crossinline execute: suspend () -> Response<T>
): Resource<T> {
    val response = try {
        execute()
    } catch (e: IOException) {
        return Resource.Error("No internet connection.")
    } catch (e: JsonParseException) {
        return Resource.Error("Serialization error.")
    } catch (e: Exception) {
        return Resource.Error("An unknown error occurred.")
    }
    return responseToResult(response)
}
