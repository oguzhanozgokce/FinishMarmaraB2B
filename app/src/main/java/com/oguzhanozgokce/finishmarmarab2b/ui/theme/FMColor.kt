package com.oguzhanozgokce.finishmarmarab2b.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColors = staticCompositionLocalOf { lightColors() }

val primaryLight = Color(0xFF007BFF)
val onPrimaryLight = Color(0xFFFFFFFF)
val errorLight = Color(0xFFFCBAAD)
val buttonLight = Color(0xFF007BFF)
val textLight = Color(0xFF000000)
val onTextLight = Color(0xFFFFFFFF)
val onBackgroundLight = Color(0xFF1B1B21)
val textSecondaryLight = Color(0xFF888888)
val backgroundLight = Color(0xFFF9F9F9)
val cardBackgroundLight = Color(0xFFFFFFFF)

val primaryDark = Color(0xFF007BFF)
val onPrimaryDark = Color(0xFFFFFFFF)
val errorDark = Color(0xFFD32F2F)
val buttonDark = Color(0xFF007BFF)
val textDark = Color(0xFFFFFFFF)
val onBackgroundDark = Color(0xFFE4E1E9)
val onTextDark = Color(0xFF000000)
val textSecondaryDark = Color(0xFF888888)
val backgroundDark = Color(0xFF222222)
val cardBackgroundDark = Color(0xFF444444)

fun lightColors(): FMColor = FMColor(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    error = errorLight,
    button = buttonLight,
    text = textLight,
    onBackground = onBackgroundLight,
    onText = onTextLight,
    textSecondary = textSecondaryLight,
    background = backgroundLight,
    cardBackground = cardBackgroundLight,
)

fun darkColors(): FMColor = FMColor(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    error = errorDark,
    button = buttonDark,
    text = textDark,
    onBackground = onBackgroundDark,
    onText = onTextDark,
    textSecondary = textSecondaryDark,
    background = backgroundDark,
    cardBackground = cardBackgroundDark,
)

@Immutable
data class FMColor(
    val primary: Color,
    val onPrimary: Color,
    val error: Color,
    val button: Color,
    val text: Color,
    val onText: Color,
    val onBackground: Color,
    val textSecondary: Color,
    val background: Color,
    val cardBackground: Color,
    val black: Color = Color.Black,
    val red: Color = Color.Red,
    val white: Color = Color.White,
    val lightGray: Color = Color.LightGray,
)
