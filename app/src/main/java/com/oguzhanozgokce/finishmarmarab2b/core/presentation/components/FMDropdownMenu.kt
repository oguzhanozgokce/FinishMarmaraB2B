package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

private const val DROPDOWN_OFFSET_X = -32
private const val DROPDOWN_OFFSET_Y = 0

data class FMDropdownMenuItem(
    val text: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@Composable
fun FMDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset((DROPDOWN_OFFSET_X).dp, DROPDOWN_OFFSET_Y.dp),
    containerColor: Color = colors.cardBackground,
    border: BorderStroke = BorderStroke(
        width = padding.dimension1,
        color = colors.primary.copy(alpha = 0.5f)
    ),
    menuItems: List<FMDropdownMenuItem>
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        offset = offset,
        containerColor = containerColor,
        border = border
    ) {
        menuItems.forEach { item ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = item.text,
                        style = FMTheme.typography.titleSmallMedium().copy(
                            fontSize = FMTheme.fontSize.mediumSmall
                        ),
                        textAlign = TextAlign.Center
                    )
                },
                onClick = {
                    onDismissRequest()
                    item.onClick()
                },
                trailingIcon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}
