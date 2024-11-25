package com.oguzhanozgokce.finishmarmarab2b.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object LMTheme {
    val colors: FMProjectColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val dimensions: FMDimension
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    val typography: FMFontSize
        @Composable
        @ReadOnlyComposable
        get() = LocalFontSizes.current

    val icons: LMIcons
        @Composable
        @ReadOnlyComposable
        get() = LocalLMIcons.current
}

@Composable
fun LMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides if (darkTheme) darkColors() else lightColors()
    ) {
        content()
    }
}