package com.oguzhanozgokce.finishmarmarab2b.core.common.extension

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.navigation.NavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(data)
    return this
}

inline fun <T> Resource<T>.onFailure(action: (String) -> Unit): Resource<T> {
    if (this is Resource.Error) action(message)
    return this
}

inline fun <T> Resource<T>.fold(
    onSuccess: (T) -> Unit,
    onError: (String) -> Unit,
) {
    when (this) {
        is Resource.Success -> onSuccess(data)
        is Resource.Error -> onError(message)
    }
}

inline fun <T : Any, N : Any> Resource<T>.toResourceMap(data: (T) -> N): Resource<N> {
    return when (this) {
        is Resource.Success -> Resource.Success(data(this.data))
        is Resource.Error -> Resource.Error(this.message)
    }
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun <T : Any, R : Any> Flow<PagingData<T>>.mapDomain(mapper: (T) -> R): Flow<PagingData<R>> {
    return map { pagingData ->
        pagingData.map { mapper(it) }
    }
}

fun <T : Any> createPager(
    pageSize: Int = 20,
    pagingSourceFactory: () -> PagingSource<Int, T>
): Flow<PagingData<T>> {
    return Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        ),
        pagingSourceFactory = pagingSourceFactory
    ).flow
}

fun NavController.navigateClearingStack(
    route: Any,
    popUpToRoute: Any,
    inclusive: Boolean = true
) {
    navigate(route) {
        popUpTo(popUpToRoute) {
            this.inclusive = inclusive
        }
        launchSingleTop = true
        restoreState = true
    }
}
