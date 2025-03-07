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
    height: Dp = 56.dp,
    enabled: Boolean = true
) {
    val containerColor = when {
        isError -> colors.primary
        enabled -> colors.cardBackground
        else -> colors.cardBackground
    }

    val textColor = if (enabled) colors.text else colors.text.copy(alpha = 0.5f)

    val indicatorColor = when {
        isError -> colors.red
        enabled -> indicatorsColor
        else -> indicatorsColor.copy(alpha = 0.2f)
    }

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
                .then(if (enabled) Modifier.clickable { onClick() } else Modifier)
                .padding(
                    horizontal = padding.dimension16,
                    vertical = padding.dimension12
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (label.isNotEmpty() && value.isEmpty()) {
                Text(
                    text = label,
                    color = textColor,
                    fontSize = FMTheme.fontSize.medium
                )
                Spacer(modifier = Modifier.width(padding.dimension8))
            }

            Text(
                text = value,
                color = textColor,
                fontSize = FMTheme.fontSize.medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .then(if (enabled) Modifier.clickable { onClick() } else Modifier)
            )

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = indicatorColor,
                modifier = Modifier.then(if (enabled) Modifier.clickable { onClick() } else Modifier)
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
