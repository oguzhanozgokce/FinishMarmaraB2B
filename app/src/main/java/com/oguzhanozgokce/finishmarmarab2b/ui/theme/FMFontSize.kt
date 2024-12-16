package com.oguzhanozgokce.finishmarmarab2b.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

internal val LocalFontSize = staticCompositionLocalOf<FMFontSize> {
    error("No default font size provided")
}

val FontSize = FMFontSize(
    small = 12.sp,
    mediumSmall = 14.sp,
    medium = 16.sp,
    body = 18.sp,
    large = 20.sp,
    extraLarge = 24.sp,
    sizeTitle = 32.sp
)

@Immutable
data class FMFontSize(
    val small: TextUnit,
    val mediumSmall: TextUnit,
    val medium: TextUnit,
    val body: TextUnit,
    val large: TextUnit,
    val extraLarge: TextUnit,
    val sizeTitle: TextUnit
)
