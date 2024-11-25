package com.oguzhanozgokce.finishmarmarab2b.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

fun lightColors(
    primary: Color = Color(0xFF9C6F91),
    customButtonColor: Color = Color(0xFFFFE5E5),
    white: Color = Color(0xFFFFFFFF),
    black: Color = Color(0xFF000000),
    semiTransparentWhite: Color = Color(0xFFEFEFEF),
    gray: Color = Color(0xFF888888),
    lightGray: Color = Color(0xFFCCCCCC),
    red: Color = Color(0xFFFF0000),
    darkGray: Color = Color(0xFF444444),
    searchBarColor: Color = Color(0xFFF3F5F9),
    searchIconColor: Color = Color(0xFF25C0FF),
    darkBlue: Color = Color(0xFF022150)
): FMProjectColor = FMProjectColor(
    primary = primary,
    customButtonColor = customButtonColor,
    white = white,
    black = black,
    semiTransparentWhite = semiTransparentWhite,
    gray = gray,
    lightGray = lightGray,
    red = red,
    darkGray = darkGray,
    searchBarColor = searchBarColor,
    searchIconColor = searchIconColor,
    darkBlue = darkBlue
)

fun darkColors(
    primary: Color = Color(0xFF9C6F91),
    customButtonColor: Color = Color(0xFFFFE5E5),
    white: Color = Color(0xFFFFFFFF),
    black: Color = Color(0xFF000000),
    semiTransparentWhite: Color = Color(0xFFEFEFEF),
    gray: Color = Color(0xFF888888),
    lightGray: Color = Color(0xFFCCCCCC),
    red: Color = Color(0xFFFF0000),
    darkGray: Color = Color(0xFF444444),
    searchBarColor: Color = Color(0xFFF3F5F9),
    searchIconColor: Color = Color(0xFF25C0FF),
    darkBlue: Color = Color(0xFF022150)
): FMProjectColor = FMProjectColor(
    primary = primary,
    customButtonColor = customButtonColor,
    white = white,
    black = black,
    semiTransparentWhite = semiTransparentWhite,
    gray = gray,
    lightGray = lightGray,
    red = red,
    darkGray = darkGray,
    searchBarColor = searchBarColor,
    searchIconColor = searchIconColor,
    darkBlue = darkBlue
)

class FMProjectColor(
    primary: Color,
    customButtonColor: Color,
    white: Color,
    black: Color,
    semiTransparentWhite: Color,
    gray: Color,
    lightGray: Color,
    red: Color,
    darkGray: Color,
    searchBarColor: Color,
    searchIconColor: Color,
    darkBlue: Color
) {
    private var _primary: Color by mutableStateOf(primary)
    val primary: Color = _primary

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

    private var _searchBarColor: Color by mutableStateOf(searchBarColor)
    val searchBarColor: Color = _searchBarColor

    private var _searchIconColor: Color by mutableStateOf(searchIconColor)
    val searchIconColor: Color = _searchIconColor

    private var _darkBlue: Color by mutableStateOf(darkBlue)
    val darkBlue: Color = _darkBlue
}

    private class KelpColorPreview {
        val primary_FFFF5722_FFFF5722: Color = Color(0xFF9C6F91)
        val primaryContainer_FF2D3142_FF2D3142: Color = Color(0xFF2D3142)
        val customButtonColor_FFFFFE5E_FFFFFE5E: Color = Color(0xFFFFE5E5)
        val white_FFFFFFFF_FFFFFFFF: Color = Color(0xFFFFFFFF)
        val black_FF000000_FF000000: Color = Color(0xFF000000)
        val semiTransparentWhite_FFB2FFFFFF_FFB2FFFFFF: Color = Color(0xFFEFEFEF)
        val gray_FF888888_FF888888: Color = Color(0xFF888888)
        val lightGray_FFCCCCCC_FFCCCCCC: Color = Color(0xFFCCCCCC)
        val red_FFFF0000_FFFF0000: Color = Color(0xFFFF0000)
        val darkGray_FF444444_FF444444: Color = Color(0xFF444444)
        val searchBarColor_FFF3F5F9_FFF3F5F9: Color = Color(0xFFF3F5F9)
        val searchIconColor_FF25C0FF_FF25C0FF: Color = Color(0xFF25C0FF)
        val darkBlue_FF022150_FF022150: Color = Color(0xFF022150)
    }

internal val LocalColors = staticCompositionLocalOf { lightColors() }