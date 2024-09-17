package com.oguzhanozgokce.finishmarmarab2b.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object MB2BTheme {
    val colors: MB2BProjectColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val dimensions: MB2BDimension
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    val typography: MB2BFontSize
        @Composable
        @ReadOnlyComposable
        get() = LocalFontSizes.current
}

@Composable
fun MB2BTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides if (darkTheme) darkColors() else lightColors()
    ) {
        content()
    }
}