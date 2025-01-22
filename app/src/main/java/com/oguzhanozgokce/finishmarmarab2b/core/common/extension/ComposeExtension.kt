package com.oguzhanozgokce.finishmarmarab2b.core.common.extension

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
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow

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

inline fun Modifier.conditional(
    condition: Boolean,
    modifier: Modifier.() -> Modifier,
): Modifier {
    return if (condition) {
        this then modifier(Modifier)
    } else {
        this
    }
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
