package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = colors.primary,
    textColor: Color = colors.white,
    enabled: Boolean = true,
    height: Dp = padding.dimension56,
    border: BorderStroke? = null,
    shape: RoundedCornerShape = RoundedCornerShape(padding.dimension8),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(2.dp),
    contentPadding: PaddingValues = PaddingValues(vertical = 12.dp, horizontal = 12.dp)
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(height),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        enabled = enabled,
        border = border,
        elevation = elevation,
        contentPadding = contentPadding
    ) {
        Text(
            text = text,
            fontSize = FMTheme.fontSize.medium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun FMOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = colors.primary,
    textColor: Color = colors.primary,
    backgroundColor: Color = colors.cardBackground,
    enabled: Boolean = true,
    height: Dp = padding.dimension56,
    elevation: ButtonElevation? = null,
    shape: Shape = RoundedCornerShape(padding.dimension8),
    fontSize: TextUnit = FMTheme.fontSize.medium,
    contentPadding: PaddingValues = PaddingValues(vertical = 12.dp, horizontal = 12.dp),
    borderWidth: Dp = 1.dp,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(height),
        border = BorderStroke(width = borderWidth, color = borderColor),
        shape = shape,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        elevation = elevation,
        enabled = enabled,
        contentPadding = contentPadding
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

@Composable
fun FMOutlinedButtonWithContent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = colors.primary,
    backgroundColor: Color = colors.white,
    enabled: Boolean = true,
    height: Dp = padding.dimension48,
    elevation: ButtonElevation? = null,
    shape: Shape = RoundedCornerShape(padding.dimension8),
    contentPadding: PaddingValues = PaddingValues(vertical = 12.dp, horizontal = 12.dp),
    borderWidth: Dp = 1.dp,
    content: @Composable () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        border = BorderStroke(width = borderWidth, color = borderColor),
        shape = shape,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor,
            contentColor = colors.primary
        ),
        elevation = elevation,
        enabled = enabled,
        contentPadding = contentPadding,
        content = { content() }
    )
}

@Preview(showBackground = true)
@Composable
fun FMButtonPreview() {
    FMTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FMButton(text = "Button", onClick = { }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(padding.dimension16))
            FMOutlinedButton(text = "Button", onClick = { }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(padding.dimension16))
            FMOutlinedButtonWithContent(onClick = { }) {
                Text(text = "Content")
            }
        }
    }
}
