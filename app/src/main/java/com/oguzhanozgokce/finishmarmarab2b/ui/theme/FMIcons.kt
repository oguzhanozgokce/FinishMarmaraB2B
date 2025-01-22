package com.oguzhanozgokce.finishmarmarab2b.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.oguzhanozgokce.finishmarmarab2b.R

class LMIcons {
    val home: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_home)
    val cart: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_cart)
    val profile: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.ic_person)
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

internal val LocalLMIcons = staticCompositionLocalOf { LMIcons() }
