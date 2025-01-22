package com.oguzhanozgokce.finishmarmarab2b.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.CustomOutlinedButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.WelcomeContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.welcome.navigation.WelcomeNavActions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun WelcomeScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    welcomeNavActions: WelcomeNavActions
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> WelcomeContent(welcomeNavActions = welcomeNavActions)
    }
}

@Composable
fun WelcomeContent(
    welcomeNavActions: WelcomeNavActions,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(padding.dimension16),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_welcomee),
            contentDescription = "Welcome Image",
            modifier = Modifier
                .fillMaxWidth()
                .size(350.dp)
                .padding(padding.dimension16),
        )
        Text(
            text = "Marmara B2B",
            style = typography.headSizeTitleThin().copy(
                fontStyle = FontStyle.Italic
            )
        )
        Text(
            text = "Welcome",
            style = typography.headExtraLargeBold()
        )
        Spacer(modifier = Modifier.height(padding.dimension48))
        FMButton(
            text = "Login",
            onClick = { welcomeNavActions.navigateToLogin() }
        )
        CustomOutlinedButton(
            text = "Register",
            onClick = { welcomeNavActions.navigateToSignup() }
        )
        Spacer(modifier = Modifier.height(padding.dimension48))
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
        welcomeNavActions = WelcomeNavActions(
            navigateToLogin = {},
            navigateToSignup = {}
        )
    )
}
