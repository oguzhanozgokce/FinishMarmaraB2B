package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

@Composable
fun FMAnnotatedText(
    prefix: String = "",
    highlightedText: String,
    prefixColor: Color = colors.text,
    highlightedColor: Color = colors.primary,
    style: TextStyle = LocalTextStyle.current,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = prefixColor)) {
                append(prefix)
            }
            withStyle(style = SpanStyle(color = highlightedColor)) {
                append(highlightedText)
            }
        },
        modifier = modifier,
        style = style.copy(color = prefixColor)
    )
}
