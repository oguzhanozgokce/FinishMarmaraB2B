package com.oguzhanozgokce.finishmarmarab2b.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object FMTheme {
    val colors: FMColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val padding: FMPadding
        @Composable
        @ReadOnlyComposable
        get() = LocalPadding.current

    val fontSize: FMFontSize
        @Composable
        @ReadOnlyComposable
        get() = LocalFontSize.current

    val icons: LMIcons
        @Composable
        @ReadOnlyComposable
        get() = LocalLMIcons.current

    val typography: FMTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
fun FMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides if (darkTheme) darkColors() else lightColors(),
        LocalPadding provides Padding,
        LocalFontSize provides FontSize,
        LocalTypography provides Typography,
        LocalLMIcons provides FMTheme.icons
    ) {
        content()
    }
}
