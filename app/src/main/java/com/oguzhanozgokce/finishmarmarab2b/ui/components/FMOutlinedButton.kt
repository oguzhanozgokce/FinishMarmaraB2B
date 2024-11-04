package com.oguzhanozgokce.finishmarmarab2b.ui.components

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
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.MB2BTheme

@Composable
fun CustomOutlinedButton(
    text: String,
    onClick: () -> Unit,
    contentColor: Color = MB2BTheme.colors.primary
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .padding(vertical = MB2BTheme.dimensions.eight)
            .height(MB2BTheme.dimensions.fiftySix)
            .fillMaxWidth(),
        border = BorderStroke(MB2BTheme.dimensions.one, contentColor),
        shape = RoundedCornerShape(MB2BTheme.dimensions.sixteen),
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = MB2BTheme.colors.white
        )

    ) {
        Text(text = text, fontSize = MB2BTheme.typography.medium, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun CustomOutlineButtonPreview() {
    CustomOutlinedButton(
        text = "Button",
        onClick = { },
        contentColor = MB2BTheme.colors.primary
    )
}