package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import kotlin.math.cos
import kotlin.math.sin

private const val DEFAULT_TOTAL_STARS = 5
private const val DEFAULT_STAR_SIZE = 12
private const val STAR_POINTS = 10
private const val STAR_ROTATION_OFFSET_DEGREE = 90
private const val STAR_ANGLE_STEP_DEGREE = 36
private const val INNER_RADIUS_RATIO = 0.5f
private const val STAR_STROKE_WIDTH = 0.1f
private const val FULL_STAR = 1f
private const val EMPTY_STAR = 0f
private const val FIRST_INDEX = 0
private const val EVEN_INDEX = 0
private const val STARTING_PAGE_INDEX = 1
private const val TWO = 2

@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier,
    totalStars: Int = DEFAULT_TOTAL_STARS,
    starSize: Dp = DEFAULT_STAR_SIZE.dp,
    filledStarColor: Color = colors.primary,
    strokeColor: Color = colors.primary,
    backgroundColor: Color = colors.background,
    spacing: Dp = 4.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        for (i in STARTING_PAGE_INDEX..totalStars) {
            val fraction = when {
                i <= rating.toFloat() -> FULL_STAR
                i - STARTING_PAGE_INDEX < rating.toFloat() -> (rating.toFloat() - (i - STARTING_PAGE_INDEX))
                else -> EMPTY_STAR
            }

            StarIcon(
                fraction = fraction,
                size = starSize,
                filledStarColor = filledStarColor,
                unfilledStarColor = strokeColor,
                backgroundColor = backgroundColor
            )
        }
    }
}

@Composable
fun StarIcon(
    fraction: Float,
    size: Dp,
    filledStarColor: Color,
    unfilledStarColor: Color,
    backgroundColor: Color
) {
    Canvas(modifier = Modifier.size(size)) {
        val radius = size.toPx() / TWO
        val innerRadius = radius * INNER_RADIUS_RATIO

        val starPath = Path().apply {
            (FIRST_INDEX until STAR_POINTS).forEach { i ->
                val angle =
                    Math.toRadians((i * STAR_ANGLE_STEP_DEGREE - STAR_ROTATION_OFFSET_DEGREE).toDouble())
                val r = if (i % TWO == EVEN_INDEX) radius else innerRadius
                val x = (radius + r * cos(angle)).toFloat()
                val y = (radius + r * sin(angle)).toFloat()
                if (i == FIRST_INDEX) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }

        drawPath(
            path = starPath,
            color = backgroundColor,
            style = Fill
        )
        drawPath(
            path = starPath,
            color = filledStarColor,
            style = Fill,
            alpha = fraction
        )
        drawPath(
            path = starPath,
            color = unfilledStarColor,
            style = Stroke(width = STAR_STROKE_WIDTH.dp.toPx())
        )
    }
}
