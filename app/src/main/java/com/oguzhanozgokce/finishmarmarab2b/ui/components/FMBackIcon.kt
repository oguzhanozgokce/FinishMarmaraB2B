
package com.oguzhanozgokce.finishmarmarab2b.ui.components

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
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.icons

@Composable
fun FMBackIcon(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(padding.dimension48)
            .padding(padding.dimension4)
            .background(
                color = colors.semiTransparentWhite,
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
                tint = colors.darkGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FMBackIconPreview() {
    FMBackIcon()
}
