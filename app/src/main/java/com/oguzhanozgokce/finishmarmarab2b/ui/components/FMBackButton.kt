package com.oguzhanozgokce.finishmarmarab2b.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.MB2BTheme

@Composable
fun BackIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(MB2BTheme.dimensions.fortyEight)
            .padding(MB2BTheme.dimensions.eight)
            .border(
                BorderStroke(MB2BTheme.dimensions.one, MB2BTheme.colors.primary),
                shape = RoundedCornerShape(MB2BTheme.dimensions.twelve)
            )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "Back",
            tint = MB2BTheme.colors.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BackIconButtonPreview() {
    BackIconButton(onClick = {})
}

