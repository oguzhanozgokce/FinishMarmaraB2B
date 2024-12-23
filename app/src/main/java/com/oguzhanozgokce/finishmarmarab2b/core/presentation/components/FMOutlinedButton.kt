package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun CustomOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = FMTheme.colors.primary
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .padding(vertical = padding.dimension8)
            .height(padding.dimension56)
            .fillMaxWidth(),
        border = BorderStroke(padding.dimension1, contentColor),
        shape = RoundedCornerShape(padding.dimension16),
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = FMTheme.colors.white
        )

    ) {
        Text(text = text, fontSize = FMTheme.fontSize.medium, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun CustomOutlineButtonPreview() {
    CustomOutlinedButton(
        text = "Button",
        onClick = { },
        contentColor = FMTheme.colors.primary
    )
}