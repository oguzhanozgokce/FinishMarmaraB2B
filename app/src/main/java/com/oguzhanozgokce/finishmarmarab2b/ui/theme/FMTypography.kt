package com.oguzhanozgokce.finishmarmarab2b.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

internal val LocalTypography = staticCompositionLocalOf<FMTypography> {
    error("No default typography provided")
}

val Typography = FMTypography(
    headSizeTitleThin = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Thin,
            fontSize = FontSize.sizeTitle,
            color = colors.text,
            lineHeight = 40.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )

    },
    headExtraLargeBold = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize.extraLarge,
            color = colors.text,
            lineHeight = 40.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    headLargeBold = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize.large,
            color = colors.text,
            lineHeight = 36.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    headMediumSemiBold = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = FontSize.medium,
            color = colors.text,
            lineHeight = 20.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    headSmallMedium = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = FontSize.small,
            color = colors.text,
            lineHeight = 18.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    titleLargeBold = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize.sizeTitle,
            color = colors.text,
            lineHeight = 40.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    titleMediumSemiBold = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = FontSize.medium,
            color = colors.text,
            lineHeight = 20.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    titleBodyBold = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize.body,
            color = colors.text,
            lineHeight = 24.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    titleMediumLight = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = FontSize.medium,
            color = colors.text,
            lineHeight = 32.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    titleSmallMedium = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = FontSize.small,
            color = colors.text,
            lineHeight = 28.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    bodyTitleBold = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize.sizeTitle,
            color = colors.text,
            lineHeight = 40.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    bodyLargeBold = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize.large,
            color = colors.text,
            lineHeight = 36.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    bodyMediumNormal = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = FontSize.body,
            color = colors.text,
            lineHeight = 32.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    bodySmallLight = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = FontSize.small,
            color = colors.text,
            lineHeight = 28.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    },
    labelLargeBold = {
        TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize.large,
            color = colors.text,
            lineHeight = 24.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    }
)

@Immutable
data class FMTypography(
    val headSizeTitleThin: @Composable () -> TextStyle,
    val headExtraLargeBold: @Composable () -> TextStyle,
    val headLargeBold: @Composable () -> TextStyle,
    val headMediumSemiBold: @Composable () -> TextStyle,
    val headSmallMedium: @Composable () -> TextStyle,
    val titleLargeBold: @Composable () -> TextStyle,
    val titleBodyBold: @Composable () -> TextStyle,
    val titleMediumSemiBold: @Composable () -> TextStyle,
    val titleMediumLight: @Composable () -> TextStyle,
    val titleSmallMedium: @Composable () -> TextStyle,
    val bodyTitleBold: @Composable () -> TextStyle,
    val bodyLargeBold: @Composable () -> TextStyle,
    val bodyMediumNormal: @Composable () -> TextStyle,
    val bodySmallLight: @Composable () -> TextStyle,
    val labelLargeBold: @Composable () -> TextStyle,
)
