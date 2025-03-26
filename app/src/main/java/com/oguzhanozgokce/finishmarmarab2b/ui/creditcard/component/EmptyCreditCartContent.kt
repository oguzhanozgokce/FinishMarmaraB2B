package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlinedButton
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

@Composable
fun EmptyCreditCartContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_add_credit_cart),
            tint = Color.Unspecified,
            contentDescription = "Add credit cart",
        )
        Spacer(modifier = Modifier.height(FMTheme.padding.dimension24))
        Text(
            text = "No credit cards yet",
            style = FMTheme.typography.headLargeBold(),
            modifier = Modifier.padding(bottom = FMTheme.padding.dimension8),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Would you like to add one now?",
            style = FMTheme.typography.bodyMediumNormal(),
            modifier = Modifier.padding(bottom = FMTheme.padding.dimension16),
            textAlign = TextAlign.Center
        )
        FMOutlinedButton(
            text = "Add Credit Card",
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = FMTheme.padding.dimension16),
            contentPadding = PaddingValues(
                vertical = FMTheme.padding.dimension8,
                horizontal = FMTheme.padding.dimension24
            )
        )
    }
}

@PreviewLightDark
@Composable
fun PreviewEmptyCreditCartContent() {
    FMTheme {
        EmptyCreditCartContent()
    }
}
