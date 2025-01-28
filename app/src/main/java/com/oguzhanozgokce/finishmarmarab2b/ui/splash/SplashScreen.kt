package com.oguzhanozgokce.finishmarmarab2b.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.ui.splash.SplashContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SplashScreen(
    uiEffect: Flow<UiEffect>,
    onNavigateToHome: () -> Unit,
    onNavigateToWelcome: () -> Unit,
) {
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.NavigateToHome -> onNavigateToHome()
            is UiEffect.NavigateToWelcome -> onNavigateToWelcome()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = null,
            tint = colors.white,
            modifier = Modifier.size(36.dp)
        )
        Text(
            text = "E-Commerce App",
            style = typography.headExtraLargeBold().copy(
                color = colors.white,
                fontSize = 36.sp
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    FMTheme {
        SplashScreen(
            uiEffect = emptyFlow(),
            onNavigateToHome = {},
            onNavigateToWelcome = {},
        )
    }
}
