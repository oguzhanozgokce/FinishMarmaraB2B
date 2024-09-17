package com.oguzhanozgokce.finishmarmarab2b.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

fun lightColors(
    primary: Color = Color(0xFF9C6F91),
    primaryContainer: Color = Color(0xFF2D3142),
    secondary: Color = Color(0xFF2196F3),
    secondaryContainer: Color = Color(0xFF009688),
    customButtonColor: Color = Color(0xFFFFE5E5),
    white: Color = Color(0xFFFFFFFF),
    black: Color = Color(0xFF000000),
    semiTransparentWhite: Color = Color(0xB2FFFFFF),
    gray: Color = Color(0xFF888888),
    lightGray: Color = Color(0xFFCCCCCC),
    red: Color = Color(0xFFFF0000),
    darkGray: Color = Color(0xFF444444)
): MB2BProjectColor = MB2BProjectColor(
    primary = primary,
    primaryContainer = primaryContainer,
    secondary = secondary,
    secondaryContainer = secondaryContainer,
    customButtonColor = customButtonColor,
    white = white,
    black = black,
    semiTransparentWhite = semiTransparentWhite,
    gray = gray,
    lightGray = lightGray,
    red = red,
    darkGray = darkGray
)

fun darkColors(
    primary: Color = Color(0xFF9C6F91),
    primaryContainer: Color = Color(0xFF2D3142),
    secondary: Color = Color(0xFF2196F3),
    secondaryContainer: Color = Color(0xFF009688),
    customButtonColor: Color = Color(0xFFFFE5E5),
    white: Color = Color(0xFFFFFFFF),
    black: Color = Color(0xFF000000),
    semiTransparentWhite: Color = Color(0xB2FFFFFF),
    gray: Color = Color(0xFF888888),
    lightGray: Color = Color(0xFFCCCCCC),
    red: Color = Color(0xFFFF0000),
    darkGray: Color = Color(0xFF444444)
): MB2BProjectColor = MB2BProjectColor(
    primary = primary,
    primaryContainer = primaryContainer,
    secondary = secondary,
    secondaryContainer = secondaryContainer,
    customButtonColor = customButtonColor,
    white = white,
    black = black,
    semiTransparentWhite = semiTransparentWhite,
    gray = gray,
    lightGray = lightGray,
    red = red,
    darkGray = darkGray
)

class MB2BProjectColor(
    primary: Color,
    primaryContainer: Color,
    secondary: Color,
    secondaryContainer: Color,
    customButtonColor: Color,
    white: Color,
    black: Color,
    semiTransparentWhite: Color,
    gray: Color,
    lightGray: Color,
    red: Color,
    darkGray: Color
) {
    private var _primary: Color by mutableStateOf(primary)
    val primary: Color = _primary

    private var _primaryContainer: Color by mutableStateOf(primaryContainer)
    val primaryContainer: Color = _primaryContainer

    private var _secondary: Color by mutableStateOf(secondary)
    val secondary: Color = _secondary

    private var _secondaryContainer: Color by mutableStateOf(secondaryContainer)
    val secondaryContainer: Color = _secondaryContainer

    private var _customButtonColor: Color by mutableStateOf(customButtonColor)
    val customButtonColor: Color = _customButtonColor

    private var _white: Color by mutableStateOf(white)
    val white: Color = _white

    private var _black: Color by mutableStateOf(black)
    val black: Color = _black

    private var _semiTransparentWhite: Color by mutableStateOf(semiTransparentWhite)
    val semiTransparentWhite: Color = _semiTransparentWhite

    private var _gray: Color by mutableStateOf(gray)
    val gray: Color = _gray

    private var _lightGray: Color by mutableStateOf(lightGray)
    val lightGray: Color = _lightGray

    private var _red: Color by mutableStateOf(red)
    val red: Color = _red

    private var _darkGray: Color by mutableStateOf(darkGray)
    val darkGray: Color = _darkGray

    private class KelpColorPreview {
        val primary_FFFF5722_FFFF5722: Color = Color(0xFF9C6F91)
        val primaryContainer_FF2D3142_FF2D3142: Color = Color(0xFF2D3142)
        val secondary_FF2196F3_FF2196F3: Color = Color(0xFF2196F3)
        val secondaryContainer_FF009688_FF009688: Color = Color(0xFF009688)
        val customButtonColor_FFFFFE5E_FFFFFE5E: Color = Color(0xFFFFE5E5)
        val white_FFFFFFFF_FFFFFFFF: Color = Color(0xFFFFFFFF)
        val black_FF000000_FF000000: Color = Color(0xFF000000)
        val semiTransparentWhite_FFB2FFFFFF_FFB2FFFFFF: Color = Color(0xB2FFFFFF)
        val gray_FF888888_FF888888: Color = Color(0xFF888888)
        val lightGray_FFCCCCCC_FFCCCCCC: Color = Color(0xFFCCCCCC)
        val red_FFFF0000_FFFF0000: Color = Color(0xFFFF0000)
        val darkGray_FF444444_FF444444: Color = Color(0xFF444444)
    }
}

internal val LocalColors = staticCompositionLocalOf { lightColors() }