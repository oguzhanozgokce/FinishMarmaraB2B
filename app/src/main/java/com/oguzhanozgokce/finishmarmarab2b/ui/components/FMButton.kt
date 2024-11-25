package com.oguzhanozgokce.finishmarmarab2b.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: Color = LMTheme.colors.primary,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(vertical = LMTheme.dimensions.eight)
            .height(LMTheme.dimensions.fiftySix)
            .fillMaxWidth(),
        shape = RoundedCornerShape(LMTheme.dimensions.sixteen),
        colors = ButtonDefaults.buttonColors(containerColor = colors),
        enabled = enabled
    ) {
        Text(text = text, fontSize = LMTheme.typography.medium, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    CustomButton(text = "Button", onClick = { })
}