package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMClickableOutlinedRow(
    modifier: Modifier = Modifier,
    label: String = "",
    value: String = "",
    isError: Boolean = false,
    errorMessage: String? = null,
    icon: ImageVector = Icons.Default.ArrowDropDown,
    indicatorsColor: Color = colors.primary.copy(alpha = 0.3f),
    onClick: () -> Unit = {},
    height: Dp = 56.dp
) {
    val containerColor = if (isError) colors.primary else colors.white
    val indicatorColor = if (isError) colors.red else indicatorsColor

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(padding.dimension8))
                .border(
                    BorderStroke(1.dp, indicatorColor),
                    shape = RoundedCornerShape(padding.dimension8)
                )
                .height(height)
                .background(containerColor)
                .clickable { onClick() }
                .padding(
                    horizontal = padding.dimension16,
                    vertical = padding.dimension12
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (label.isNotEmpty() && value.isEmpty()) {
                Text(
                    text = label,
                    color = colors.text,
                    fontSize = FMTheme.fontSize.medium
                )
                Spacer(modifier = Modifier.width(padding.dimension8))
            }

            Text(
                text = value,
                color = colors.text,
                fontSize = FMTheme.fontSize.medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onClick() }
            )

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = indicatorColor,
                modifier = Modifier.clickable { onClick() }
            )
        }

        if (isError && !errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage,
                style = FMTheme.typography.bodyMediumNormal().copy(
                    color = colors.error
                ),
                modifier = Modifier.padding(
                    start = padding.dimension16,
                    top = padding.dimension4
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FMClickableOutlinedRowPreview() {
    FMTheme {
        FMClickableOutlinedRow(
            label = "LABEL",
            value = "",
            isError = false,
            errorMessage = null,
            onClick = {}
        )
    }
}
