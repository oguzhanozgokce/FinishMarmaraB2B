package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: Color = FMTheme.colors.primary,
    enabled: Boolean = true,
    height: Dp = padding.dimension56,
    border: BorderStroke? = null,
    shape: RoundedCornerShape = RoundedCornerShape(padding.dimension16),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(4.dp)
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        shape = shape,
        colors = ButtonDefaults.buttonColors(containerColor = colors),
        enabled = enabled,
        border = border,
        elevation = elevation
    ) {
        Text(
            text = text,
            fontSize = FMTheme.fontSize.medium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FMButtonPreview() {
    FMTheme {
        FMButton(text = "Button", onClick = { })
    }
}