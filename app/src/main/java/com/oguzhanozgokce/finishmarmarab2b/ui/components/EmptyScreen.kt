package com.oguzhanozgokce.finishmarmarab2b.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.dimensions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.typography
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(120.dp),
            imageVector = Icons.Default.Warning,
            contentDescription = "Warning",
        )
        Text(
            text = "No items found",
            color = Color.Black,
            fontSize = 20.sp,
        )
    }
}

@Composable
fun EmptyFavoriteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensions.sixteen),
        verticalArrangement = Arrangement
            .spacedBy(dimensions.sixteen, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(dimensions.oneHundredTwenty),
            imageVector = icons.favoriteBorder,
            contentDescription = "Favorite Icon",
            tint = colors.red
        )
        Text(
            text = stringResource(id = R.string.empty_favorites_title),
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = typography.large,
            color = colors.black,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.empty_favorites_description),
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = typography.medium,
            color = colors.darkGray,
            textAlign = TextAlign.Center
        )
        TextButton(
            onClick = { /* TODO: Navigate to the home page */ },
            modifier = Modifier
                .padding(dimensions.sixteen)
                .border(
                    BorderStroke(dimensions.one, colors.black),
                    shape = RoundedCornerShape(dimensions.fortyEight)
                )
                .padding(horizontal = dimensions.sixteen, vertical = dimensions.eight)
        ) {
            Text(
                text = stringResource(id = R.string.add_to_favorites_button),
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = typography.medium,
                color = colors.red
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    EmptyFavoriteScreen()
}