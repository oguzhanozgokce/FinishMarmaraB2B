package com.oguzhanozgokce.finishmarmarab2b.core.common.extension

import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.navigation.NavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.mapToProduct
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.PaginationData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

fun Int?.orZero(): Int = this ?: 0
fun Double?.orDoubleZero(): Double = this ?: 0.0
fun String?.orEmpty(): String = this ?: ""
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun String.capitalizeFirstLetter(): String {
    return this.lowercase().replaceFirstChar { it.uppercaseChar() }
}

fun User.getFormattedName(): String {
    val formattedSurname = if (surname.isNotEmpty()) "${surname.first()}." else ""
    return "$name $formattedSurname"
}

fun String.formatPhoneNumber(): String {
    val cleanedNumber = this.filter { it.isDigit() }
        .removePrefix("0")

    if (cleanedNumber.length != 10) return this

    return "${cleanedNumber.take(3)}*****${cleanedNumber.takeLast(2)}"
}

fun User.getInitials(): String {
    val nameInitial = name.firstOrNull()?.uppercaseChar() ?: ""
    val surnameInitial = surname.firstOrNull()?.uppercaseChar() ?: ""
    return "$nameInitial$surnameInitial"
}

fun String?.toLocalDateOrDefault(
    formatter: DateTimeFormatter,
    defaultDate: LocalDate = LocalDate.now()
): LocalDate {
    return this?.let {
        try {
            LocalDateTime.parse(it, formatter).toLocalDate()
        } catch (e: Exception) {
            defaultDate
        }
    } ?: defaultDate
}

fun String?.toLocalDateTimeOrDefault(
    formatter: DateTimeFormatter,
    defaultDateTime: LocalDateTime = LocalDateTime.now()
): LocalDateTime {
    return this?.let {
        try {
            LocalDateTime.parse(it, formatter)
        } catch (e: Exception) {
            defaultDateTime
        }
    } ?: defaultDateTime
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

fun PaginationData<ProductDto>.mapToPaginationData(): PaginationData<Product> {
    return PaginationData(
        list = this.list?.map { it.mapToProduct() } ?: emptyList(),
        pagination = this.pagination
    )
}

fun NavController.navigateClearingStack(
    route: Screen,
    popUpToRoute: Screen,
    inclusive: Boolean = true
) {
    navigate(route) {
        popUpTo(popUpToRoute) {
            this.inclusive = inclusive
        }
    }
}
