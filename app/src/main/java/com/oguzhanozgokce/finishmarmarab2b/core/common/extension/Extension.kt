package com.oguzhanozgokce.finishmarmarab2b.core.common.extension

import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
fun Boolean?.orFalse(): Boolean = this ?: false
fun String?.orEmpty(): String = this ?: ""


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

fun String?.toLocalDateTimeOrDefault(
    formatter: DateTimeFormatter,
    defaultDateTime: LocalDateTime = LocalDateTime.now()
): LocalDateTime {
    return this?.let {
        try {
            LocalDateTime.parse(it, formatter)
        } catch (e: Exception) {
            Log.e("DateParsing", "Invalid date format: $it, using default date and time.", e)
            defaultDateTime
        }
    } ?: defaultDateTime
}


@Composable
fun Modifier.shimmer(
    isLoading: Boolean,
    shape: Shape = MaterialTheme.shapes.medium,
    shimmerColors: Color = Color.Gray.copy(alpha = 0.2f),
    placeholderColor: Color = Color.Gray.copy(alpha = 0.1f),
    durationMillis: Int = 1000
): Modifier {
    if (!isLoading) return this
    val transition = rememberInfiniteTransition(label = "")

    val shimmerProgress = transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Shimmer Progress"
    )

    return this.drawWithContent {
        val outline = shape.createOutline(size, layoutDirection, this)
        drawOutline(outline, color = placeholderColor)
        drawOutline(outline, color = shimmerColors, alpha = shimmerProgress.value)
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

@Composable
fun <T : Any> mockLazyPagingItems(data: List<T>): LazyPagingItems<T> {
    val pagingSource = object : PagingSource<Int, T>() {
        override fun getRefreshKey(state: PagingState<Int, T>): Int? = null

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
            return LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = null
            )
        }
    }

    val pager = Pager(PagingConfig(pageSize = data.size)) {
        pagingSource
    }

    return pager.flow.collectAsLazyPagingItems()
}


