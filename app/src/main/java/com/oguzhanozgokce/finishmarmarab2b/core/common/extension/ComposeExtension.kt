package com.oguzhanozgokce.finishmarmarab2b.core.common.extension

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

private const val GRADIENT_WIDTH = 0.5f

@Composable
fun Modifier.shimmer(
    isLoading: Boolean,
    shape: Shape = MaterialTheme.shapes.medium,
    durationMillis: Int = 1000,
    baseColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    highlightColor: Color = Color.White.copy(alpha = 0.7f)
): Modifier {
    if (!isLoading) return this
    val transition = rememberInfiniteTransition(label = "ShimmerTransition")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "ShimmerProgress"
    )

    return this.drawWithCache {
        val path = when (val outline = shape.createOutline(size, layoutDirection, this)) {
            is Outline.Rectangle -> Path().apply { addRect(Rect(Offset.Zero, size)) }
            is Outline.Rounded -> Path().apply { addRoundRect(outline.roundRect) }
            is Outline.Generic -> outline.path
        }

        val gradientWidth = size.width * GRADIENT_WIDTH
        val startX = -gradientWidth
        val endX = size.width + gradientWidth
        val translateX = startX + (endX - startX) * progress

        val brush = Brush.linearGradient(
            colors = listOf(
                baseColor.copy(alpha = 0.1f),
                baseColor.copy(alpha = 0.2f),
                highlightColor.copy(alpha = 0.3f),
                baseColor.copy(alpha = 0.2f),
                baseColor.copy(alpha = 0.1f)
            ),
            start = Offset(x = translateX - gradientWidth, y = 0f),
            end = Offset(x = translateX + gradientWidth, y = 0f)
        )

        onDrawBehind {
            drawPath(path = path, brush = brush)
        }
    }
}

@Preview(name = "Shimmer Light", showBackground = true)
@Composable
fun ShimmerLightPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .size(100.dp)
                .shimmer(isLoading = true, shape = RoundedCornerShape(16.dp))
        )
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
