package com.oguzhanozgokce.finishmarmarab2b.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.oguzhanozgokce.finishmarmarab2b.R

internal val LocalLMIcons = staticCompositionLocalOf { LMIcons() }

class LMIcons {
    val cart: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_cart)
    val notification: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_notification)
    val favorite: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_favorite)
    val favoriteBorder: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_favorite_border)
    val back: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_back)
    val starsOutline: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_star_outline)
    val starsHalf: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_star_half)
}
