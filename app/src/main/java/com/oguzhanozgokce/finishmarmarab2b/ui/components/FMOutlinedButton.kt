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
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme

@Composable
fun CustomOutlinedButton(
    text: String,
    onClick: () -> Unit,
    contentColor: Color = LMTheme.colors.primary
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .padding(vertical = LMTheme.dimensions.eight)
            .height(LMTheme.dimensions.fiftySix)
            .fillMaxWidth(),
        border = BorderStroke(LMTheme.dimensions.one, contentColor),
        shape = RoundedCornerShape(LMTheme.dimensions.sixteen),
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = LMTheme.colors.white
        )

    ) {
        Text(text = text, fontSize = LMTheme.typography.medium, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun CustomOutlineButtonPreview() {
    CustomOutlinedButton(
        text = "Button",
        onClick = { },
        contentColor = LMTheme.colors.primary
    )
}