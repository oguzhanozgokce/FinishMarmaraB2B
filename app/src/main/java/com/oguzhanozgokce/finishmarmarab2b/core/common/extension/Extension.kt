package com.oguzhanozgokce.finishmarmarab2b.core.common.extension

import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@Composable
fun <T> Flow<T>.CollectWithLifecycle(
    collect: suspend (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(this, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@CollectWithLifecycle.collect { effect ->
                collect(effect)
            }
        }
    }
}

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

inline fun <T : Any, N : Any> Resource<T>.toResourceMap(data: (T) -> N): Resource<N> {
    return when (this) {
        is Resource.Success -> Resource.Success(data(this.data))
        is Resource.Error -> Resource.Error(this.message)
    }
}

fun Int?.orZero(): Int = this ?: 0
fun Double?.orZero(): Double = this ?: 0.0
fun Boolean?.orFalse(): Boolean = this ?: false
fun String?.orEmpty(): String = this ?: ""
fun Int.isPositive(): Boolean = this > 0


inline fun Modifier.noRippleClickable(
    crossinline onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

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

fun User.getInitials(): String {
    val nameInitial = name.firstOrNull()?.uppercaseChar() ?: ""
    val surnameInitial = surname.firstOrNull()?.uppercaseChar() ?: ""
    return "$nameInitial$surnameInitial"
}

fun String?.toLocalDateOrDefault(formatter: DateTimeFormatter, defaultDate: LocalDate = LocalDate.now()): LocalDate {
    return this?.let {
        try {
            LocalDateTime.parse(it, formatter).toLocalDate()
        } catch (e: Exception) {
            Log.e("DateParsing", "Invalid date format: $it, using default date.", e)
            defaultDate
        }
    } ?: defaultDate
}


