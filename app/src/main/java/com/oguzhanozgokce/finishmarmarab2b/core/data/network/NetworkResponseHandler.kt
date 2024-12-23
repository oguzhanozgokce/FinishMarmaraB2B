package com.oguzhanozgokce.finishmarmarab2b.core.data.network

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse


fun <T> responseToResult(response: ApiResponse<T>): Resource<T> {
    return if (response.status) {
        response.data.let { Resource.Success(it) }
    } else {
        Resource.Error(response.message.ifEmpty { "An unknown error occurred." })
    }
}
