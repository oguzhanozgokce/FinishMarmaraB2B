package com.oguzhanozgokce.finishmarmarab2b.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalPadding = staticCompositionLocalOf<FMPadding> {
    error("No default padding provided")
}

@Immutable
data class FMPadding(
    val dimension1: Dp,
    val dimension2: Dp,
    val dimension4: Dp,
    val dimension6: Dp,
    val dimension8: Dp,
    val dimension12: Dp,
    val dimension16: Dp,
    val dimension20: Dp,
    val dimension24: Dp,
    val dimension32: Dp,
    val dimension36: Dp,
    val dimension48: Dp,
    val dimension56: Dp,
    val dimension60: Dp,
    val dimension64: Dp,
    val dimension80: Dp,
    val dimension100: Dp,
    val dimension120: Dp,
    val dimension140: Dp,
    val dimension160: Dp,
    val dimension180: Dp,
    val dimension200: Dp,
    val dimension260: Dp

)

val Padding = FMPadding(
    dimension1 = 1.dp,
    dimension2 = 2.dp,
    dimension4 = 4.dp,
    dimension6 = 6.dp,
    dimension8 = 8.dp,
    dimension12 = 12.dp,
    dimension16 = 16.dp,
    dimension20 = 20.dp,
    dimension24 = 24.dp,
    dimension32 = 32.dp,
    dimension36 = 36.dp,
    dimension48 = 48.dp,
    dimension56 = 56.dp,
    dimension60 = 60.dp,
    dimension64 = 64.dp,
    dimension80 = 80.dp,
    dimension100 = 100.dp,
    dimension120 = 120.dp,
    dimension140 = 140.dp,
    dimension160 = 160.dp,
    dimension180 = 180.dp,
    dimension200 = 200.dp,
    dimension260 = 260.dp
)
