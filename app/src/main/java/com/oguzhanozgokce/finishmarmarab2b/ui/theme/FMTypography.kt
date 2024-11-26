package com.oguzhanozgokce.finishmarmarab2b.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

internal val LocalTypography = staticCompositionLocalOf<FMTypography> {
    error("No default typography provided")
}

val Typography = FMTypography(
    headlineLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.extraLarge,
        color = Color.Black
    ),
    headlineMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = FontSize.large,
        color = Color.Black
    ),
    headlineSmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = FontSize.medium,
        color = Color.Black
    ),
    titleMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = FontSize.medium,
        color = Color.Black
    ),
    bodySize = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.sizeTitle,
        color = Color.Black
    ),
    bodyLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.large,
        color = Color.Black
    ),
    bodyPrimary = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = FontSize.body,
        color = Color.DarkGray
    ),
    bodySmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = FontSize.small,
        color = Color.Gray
    ),
    labelLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.large,
        color = Color.Black
    )
)

@Immutable
data class FMTypography(
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,
    val titleMedium: TextStyle,
    val bodySize: TextStyle,
    val bodyLarge: TextStyle,
    val bodyPrimary: TextStyle,
    val bodySmall: TextStyle,
    val labelLarge: TextStyle,
)
