package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun FMHorizontalDivider(
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
