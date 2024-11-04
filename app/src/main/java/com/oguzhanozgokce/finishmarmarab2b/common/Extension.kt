package com.oguzhanozgokce.finishmarmarab2b.common

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

inline fun <T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(data)
    return this
}

inline fun <T> Resource<T>.onFailure(action: (String) -> Unit): Resource<T> {
    if (this is Resource.Error) action(message)
    return this
}

@OptIn(ExperimentalContracts::class)
fun <T : Any> Resource<T>.isSuccess(): Boolean {
    contract {
        returns(true) implies (this@isSuccess is Resource.Success<T>)
        returns(false) implies (this@isSuccess is Resource.Error)
    }
    return this is Resource.Success
}

inline fun <T : Any, N : Any> Resource<T>.map(data: (T) -> N): Resource<N> {
    return when (this) {
        is Resource.Success -> Resource.Success(data(this.data))
        is Resource.Error -> {
            Resource.Error(this.message)
        }
    }
}

