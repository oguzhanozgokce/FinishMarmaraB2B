package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun FMCard(
    modifier: Modifier = Modifier.background(colors.white).padding(padding.dimension8),
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    elevation: CardElevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    cardColors: CardColors = CardDefaults.cardColors(
        containerColor = colors.white
    ),
    border: BorderStroke? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = shape,
        elevation = elevation,
        colors = cardColors,
        border = border,
        content = { content() }
    )
}

@Composable
fun FMOutlinedCard(
    modifier: Modifier = Modifier.background(colors.white).padding(padding.dimension8),
    shape: Shape = RoundedCornerShape(8.dp),
    cardColors: CardColors = CardDefaults.outlinedCardColors(containerColor = colors.white),
    border: BorderStroke = BorderStroke(1.dp, colors.primary.copy(alpha = 0.4f)),
    content: @Composable () -> Unit
) {
    OutlinedCard(
        modifier = modifier,
        shape = shape,
        colors = cardColors,
        border = border,
        content = { content() }
    )
}

@Preview(showBackground = true)
@Composable
fun FMCardPreview() {
    FMTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FMCard(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    Text(
                        text = "FMCard Example",
                        modifier = Modifier.padding(16.dp),
                        style = typography.bodyMediumNormal()
                    )
                }
            )

            FMOutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    Text(
                        text = "FMOutlinedCard Example",
                        modifier = Modifier.padding(16.dp),
                        style = typography.bodyMediumNormal()
                    )
                }
            )
        }
    }
}
