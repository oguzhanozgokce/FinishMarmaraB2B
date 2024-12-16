package com.oguzhanozgokce.finishmarmarab2b.core.data.network.error

import com.oguzhanozgokce.finishmarmarab2b.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import retrofit2.Response

/**
 * Converts an ApiResponse<T> into a Resource<T>.
 * - If the response is successful (status = true), wraps the data in a Resource.Success.
 * - If the response has no data, returns a Resource.Error with a null body message.
 * - If the response status is false, wraps the message in a Resource.Error.
 */
fun <T> responseToResult(response: ApiResponse<T>): Resource<T> {
    return if (response.status) {
        response.data?.let { Resource.Success(it) }
            ?: Resource.Error("Response body is null.")
    } else {
        Resource.Error(response.message)
    }
}
