package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme

@Composable
fun FMHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    color: Color = FMTheme.colors.gray.copy(alpha = 0.5f),
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    HorizontalDivider(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues),
        thickness = thickness,
        color = color
    )
}
