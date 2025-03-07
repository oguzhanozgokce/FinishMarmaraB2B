package com.oguzhanozgokce.finishmarmarab2b.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.BackIconButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.CustomIconButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMAlertDialog
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlineTextField
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.login.navigation.LoginNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun LoginScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    loginNavActions: LoginNavActions,
) {
    var alertDialogState by remember { mutableStateOf(false) }
    val context = LocalContext.current

    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> LoginContent(
            uiState = uiState,
            onAction = onAction,
            loginNavActions = loginNavActions
        )
    }

    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowAlertDialog -> alertDialogState = true
            is UiEffect.GoToForgotPassword -> loginNavActions.navigateToForgotPassword()
            is UiEffect.GoToBack -> loginNavActions.navigateToBack()
            is UiEffect.GoToHome -> loginNavActions.navigateToHome()
            is UiEffect.ShowToast -> {
                context.showToast(effect.message)
            }
        }
    }
    if (alertDialogState) {
        FMAlertDialog(
            errorMessage = uiState.error,
            onDismiss = { alertDialogState = false },
            confirmButtonClickListener = {
                onAction(UiAction.ClearError)
                alertDialogState = false
            }
        )
    }
}

@Composable
fun LoginContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    loginNavActions: LoginNavActions,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background)
            .padding(padding.dimension16),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BackIconButton(
            onClick = loginNavActions.navigateToBack,
            modifier = Modifier
                .padding(vertical = padding.dimension8)
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(padding.dimension20))
        Text(
            text = stringResource(id = R.string.welcome_login_text),
            style = FMTheme.typography.headSizeTitleThin(),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(padding.dimension64))
        FMOutlineTextField(
            value = uiState.email,
            onValueChange = { onAction(UiAction.EmailChanged(it)) },
            label = "Email",
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )
        Spacer(modifier = Modifier.height(padding.dimension16))
        FMOutlineTextField(
            value = uiState.password,
            onValueChange = { onAction(UiAction.PasswordChanged(it)) },
            label = "Password",
            isPassword = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password"
                )
            },
        )
        Spacer(modifier = Modifier.height(padding.dimension8))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_forgot_password),
                contentDescription = null,
                modifier = Modifier.size(padding.dimension20)
            )
            Text(
                modifier = Modifier.clickable { loginNavActions.navigateToForgotPassword() },
                text = stringResource(id = R.string.forgot_password_text),
                fontWeight = FontWeight.Light,
                fontSize = FMTheme.fontSize.small,
            )
        }
        Spacer(modifier = Modifier.height(padding.dimension32))
        FMButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Login",
            onClick = { onAction(UiAction.LoginClicked) }
        )
        Spacer(modifier = Modifier.height(padding.dimension16))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding.dimension16),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FMHorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = padding.dimension1,
                color = FMTheme.colors.primary
            )

            Spacer(modifier = Modifier.width(padding.dimension8))

            Text(
                text = stringResource(id = R.string.or_login_with_text),
                fontSize = FMTheme.fontSize.medium,
                color = FMTheme.colors.text
            )

            Spacer(modifier = Modifier.width(padding.dimension8))

            FMHorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = padding.dimension1,
                color = FMTheme.colors.primary
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding.dimension16),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomIconButton(
                iconResId = R.drawable.ic_google,
                onClickAction = { onAction(UiAction.LoginClicked) },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = (0xFFF5F5F5))
@Composable
fun LoginScreenPreview(
    @PreviewParameter(LoginScreenPreviewProvider::class) uiState: UiState,
) {
    FMTheme {
        LoginScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            loginNavActions = LoginNavActions(
                navigateToForgotPassword = {},
                navigateToBack = {},
                navigateToHome = {},
            )
        )
    }
}
