package com.oguzhanozgokce.finishmarmarab2b.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.common.collectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomButton
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomOutlinedButton
import com.oguzhanozgokce.finishmarmarab2b.ui.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LocalDimensions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LocalFontSizes
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.MB2BTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun WelcomeScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToLogin: () -> Unit = {},
    onNavigateToSignup: () -> Unit = {},
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> WelcomeContent(
            onNavigateToLogin = onNavigateToLogin,
            onNavigateToSignup = onNavigateToSignup
        )
    }

    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.GoToLogin -> {onNavigateToLogin()}
            is UiEffect.GoToSignup -> {onNavigateToSignup()}
        }
    }
}

@Composable
fun WelcomeContent(
    onNavigateToLogin: () -> Unit = {},
    onNavigateToSignup: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MB2BTheme.dimensions.sixteen),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_welcome),
            contentDescription = "Welcome Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(MB2BTheme.dimensions.sixteen)
        )
        Text(
            text = "Marmara B2B",
            fontSize = MB2BTheme.typography.sizeTitle,
            fontWeight = FontWeight.Thin,
            fontStyle = FontStyle.Italic,
        )
        Text(
            text = "Welcome",
            fontSize = MB2BTheme.typography.sizeTitle,
            fontWeight = FontWeight.Normal,
        )
        Spacer(modifier = Modifier.height(MB2BTheme.dimensions.fortyEight))
        CustomButton(text = "Login", onClick = {onNavigateToLogin()})
        CustomOutlinedButton(text = "Register" , onClick = {onNavigateToSignup()})
        Spacer(modifier = Modifier.height(MB2BTheme.dimensions.fortyEight))
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview(
    @PreviewParameter(WelcomeScreenPreviewProvider::class) uiState: UiState,
) {
    WelcomeScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        onNavigateToLogin = {},
        onNavigateToSignup = {},
    )
}