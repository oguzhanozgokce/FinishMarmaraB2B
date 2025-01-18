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

@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier,
    totalStars: Int = 5,
    starSize: Dp = 12.dp,
    filledStarColor: Color = colors.primary,
    strokeColor: Color = colors.primary,
    backgroundColor: Color = colors.background,
    spacing: Dp = 4.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        for (i in 1..totalStars) {
            val fraction = when {
                i <= rating.toFloat() -> 1f
                i - 1 < rating.toFloat() -> (rating.toFloat() - (i - 1))
                else -> 0f
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
        val radius = size.toPx() / 2
        val innerRadius = radius * 0.5f

        val starPath = Path().apply {
            (0 until 10).forEach { i ->
                val angle = Math.toRadians((i * 36 - 90).toDouble())
                val r = if (i % 2 == 0) radius else innerRadius
                val x = (radius + r * cos(angle)).toFloat()
                val y = (radius + r * sin(angle)).toFloat()
                if (i == 0) moveTo(x, y) else lineTo(x, y)
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
            style = Stroke(width = 0.1.dp.toPx())
        )
    }
}
