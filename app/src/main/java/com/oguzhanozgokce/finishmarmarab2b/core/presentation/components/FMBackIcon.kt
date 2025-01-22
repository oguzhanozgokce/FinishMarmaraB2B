
package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMBackIcon(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(padding.dimension48)
            .padding(padding.dimension4)
            .background(
                color = colors.cardBackground,
                shape = RoundedCornerShape(padding.dimension16)
            ),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = { }
        ) {
            Icon(
                imageVector = icons.back,
                contentDescription = "Back",
                tint = colors.text
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FMBackIconPreview() {
    FMBackIcon()
}
