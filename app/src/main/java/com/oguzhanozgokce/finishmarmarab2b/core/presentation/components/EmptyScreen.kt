package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(color = colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(padding.dimension120),
            imageVector = Icons.Default.Warning,
            contentDescription = "Warning",
        )
        Text(
            text = "No items found",
            style = typography.titleMediumMedium(),
        )
    }
}

@Composable
fun EmptyImageScreen(
    modifier: Modifier = Modifier,
    message: String = "No images available",
    iconSize: Dp = padding.dimension100,
    textColor: Color = colors.text,
    iconTint: Color = colors.onBackground
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colors.cardBackground)
            .padding(horizontal = padding.dimension16, vertical = padding.dimension8),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            imageVector = Icons.Default.Warning,
            contentDescription = "Warning Icon",
            tint = iconTint
        )
        Spacer(modifier = Modifier.height(padding.dimension8))
        Text(
            text = message,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize.medium,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EmptyFavoriteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(padding.dimension16),
        verticalArrangement = Arrangement
            .spacedBy(padding.dimension16, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(padding.dimension120),
            imageVector = icons.favoriteBorder,
            contentDescription = "Favorite Icon",
            tint = colors.red
        )
        Text(
            text = stringResource(id = R.string.empty_favorites_title),
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize.large,
            color = colors.text,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.empty_favorites_description),
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = fontSize.medium,
            color = colors.text,
            textAlign = TextAlign.Center
        )
        TextButton(
            onClick = { /* TODO: Navigate to the home page */ },
            modifier = Modifier
                .padding(padding.dimension16)
                .border(
                    BorderStroke(padding.dimension1, colors.onBackground),
                    shape = RoundedCornerShape(padding.dimension48)
                )
                .padding(horizontal = padding.dimension16, vertical = padding.dimension8)
        ) {
            Text(
                text = stringResource(id = R.string.add_to_favorites_button),
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = fontSize.medium,
                color = colors.red
            )
        }
    }
}

@PreviewLightDark
@Composable
fun EmptyImageScreenPreview() {
    FMTheme {
        EmptyImageScreen()
    }
}

@PreviewLightDark
@Composable
fun EmptyScreenPreview() {
    FMTheme {
        EmptyFavoriteScreen()
    }
}
