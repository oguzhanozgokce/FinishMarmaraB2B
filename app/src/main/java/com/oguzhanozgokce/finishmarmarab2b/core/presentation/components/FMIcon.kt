package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMIcon(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    boxSize: Dp = padding.dimension36,
    icon: @Composable () -> Unit,
    shape: Shape = CircleShape,
    onClick: () -> Unit = {},
    border: BorderStroke? = null,
) {
    Box(
        modifier = modifier
            .size(boxSize)
            .padding(padding.dimension4)
            .background(color = backgroundColor, shape = shape)
            .border(border ?: BorderStroke(0.dp, Color.Transparent), shape)
            .noRippleClickable { onClick() },
        contentAlignment = Alignment.Center,
        content = { icon() }
    )
}

@Composable
fun FMFavoriteButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = colors.background,
    boxSize: Dp = padding.dimension36,
    isFavorite: Boolean = false,
    border: BorderStroke? = null,
    onClick: () -> Unit,
) {
    FMIcon(
        modifier = modifier,
        backgroundColor = backgroundColor,
        boxSize = boxSize,
        onClick = onClick,
        border = border,
        icon = {
            Icon(
                imageVector = if (isFavorite) icons.favorite else icons.favoriteBorder,
                contentDescription = null,
                tint = colors.red,
            )
        }
    )
}

@Composable
fun FMIconButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = colors.background,
    tintColor: Color = colors.primary,
    boxSize: Dp = padding.dimension36,
    iconResId: Int? = null,
    iconVector: ImageVector? = null,
    shape: Shape = CircleShape,
    onClick: () -> Unit = {},
    border: BorderStroke? = null,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(boxSize)
            .background(color = backgroundColor, shape = shape)
            .border(border ?: BorderStroke(0.dp, Color.Transparent), shape),
    ) {
        when {
            iconResId != null -> {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    tint = tintColor,
                    modifier = Modifier.size(padding.dimension24)
                )
            }

            iconVector != null -> {
                Icon(
                    imageVector = iconVector,
                    contentDescription = null,
                    tint = tintColor,
                    modifier = Modifier.size(padding.dimension24)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFMIcon() {
    FMTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(padding.dimension8)
        ) {
            FMIcon(
                backgroundColor = colors.white,
                border = BorderStroke(1.dp, colors.lightGray),
                icon = {
                    Icon(
                        imageVector = icons.favorite,
                        contentDescription = null,
                        tint = colors.primary
                    )
                }
            )

            FMFavoriteButton(
                backgroundColor = colors.primary,
                isFavorite = true,
                onClick = {},
            )

            FMIconButton(
                iconResId = R.drawable.ic_google,
                onClick = {},
                border = BorderStroke(1.dp, colors.primary),
                tintColor = Color.Unspecified,
                boxSize = 36.dp
            )
        }
    }
}
