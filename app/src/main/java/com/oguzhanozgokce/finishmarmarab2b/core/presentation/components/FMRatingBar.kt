package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily
import java.util.Locale

@Composable
fun FMRatingBar(
    rating: Float,
    totalStars: Int = 5,
    modifier: Modifier = Modifier,
    onRatingChanged: ((Float) -> Unit)? = null,
    starSize: Dp = 24.dp,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFontFamily
    )
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(totalStars) { index ->
            val isActive = index < rating.toInt()
            val isHalfStar = (rating - index).let { it > 0 && it < 1 }

            Icon(
                imageVector = when {
                    isHalfStar -> icons.starsHalf
                    isActive -> Icons.Default.Star
                    else -> icons.starsOutline
                },
                contentDescription = "Rating Star $index",
                tint = if (isActive || isHalfStar) activeColor else inactiveColor,
                modifier = Modifier
                    .size(starSize)
                    .run {
                        if (onRatingChanged != null) {
                            clickable {
                                onRatingChanged((index + 1).toFloat())
                            }
                        } else {
                            this
                        }
                    }
            )
        }

        Text(
            text = String.format(Locale.US, "%.1f", rating),
            style = textStyle,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RatingBarPreview() {
    FMRatingBar(
        rating = 4.5f,
        onRatingChanged = { newRating -> println("New rating: $newRating") }
    )
}
