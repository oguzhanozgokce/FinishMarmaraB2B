package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme

@Composable
fun FMVerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    color: Color = FMTheme.colors.lightGray,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    height: Dp = Dp.Unspecified
) {
    VerticalDivider(
        modifier = modifier
            .then(if (height != Dp.Unspecified) Modifier.height(height) else Modifier)
            .padding(paddingValues),
        thickness = thickness,
        color = color,
    )
}
