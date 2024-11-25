package com.oguzhanozgokce.finishmarmarab2b.ui.components


import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun CustomDivider(
    modifier: Modifier = Modifier,
    thickness: Dp,
    color: Color
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}

@Composable
fun CustomVerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp,
    color: Color
) {
    VerticalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}
