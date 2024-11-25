package com.oguzhanozgokce.finishmarmarab2b.ui.login

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.ui.components.BackIconButton
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomAlertDialog
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomButton
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomDivider
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomIconButton
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomTextField
import com.oguzhanozgokce.finishmarmarab2b.ui.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun LoginScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToBack: () -> Unit,
) {
    var alertDialogState by remember { mutableStateOf(false) }

    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> LoginContent(
            uiState = uiState,
            onAction = onAction,
            onNavigateToForgotPassword = onNavigateToForgotPassword,
            onNavigateToBack = onNavigateToBack
        )
    }

    uiEffect.CollectWithLifecycle { effect ->
        when(effect){
            is UiEffect.ShowAlertDialog -> alertDialogState = true
            is UiEffect.GoToForgotPassword -> onNavigateToForgotPassword()
            is UiEffect.GoToBack -> onNavigateToBack()
        }
    }
    if (alertDialogState){
        CustomAlertDialog(
            errorMessage = uiState.error,
            onDismiss = { alertDialogState = false },
            confirmButtonClickListener = { onAction(UiAction.ClearError)
            alertDialogState = false
            }
        )
    }
}

@Composable
fun LoginContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LMTheme.dimensions.sixteen),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BackIconButton(
            onClick = onNavigateToBack,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(LMTheme.dimensions.twenty))
        Text(
            text = stringResource(id = R.string.welcome_login_text),
            fontSize = LMTheme.typography.sizeTitle,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(LMTheme.dimensions.twelve))
        CustomTextField(
            value = uiState.email,
            onValueChange = {onAction(UiAction.EmailChanged(it))},
            label = "Email",
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )
        Spacer(modifier = Modifier.height(LMTheme.dimensions.sixteen))
        CustomTextField(
            value = uiState.password,
            onValueChange = {onAction(UiAction.PasswordChanged(it))},
            label = "Password",
            isPassword = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password"
                )
            },
        )
        Spacer(modifier = Modifier.height(LMTheme.dimensions.eight))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_forgot_password),
                contentDescription = null,
                modifier = Modifier.size(LMTheme.dimensions.twenty)
            )
            Text(
                modifier = Modifier.clickable {onNavigateToForgotPassword()},
                text = stringResource(id = R.string.forgot_password_text),
                fontWeight = FontWeight.Light,
                fontSize = LMTheme.typography.small,
                )
        }
        Spacer(modifier = Modifier.height(LMTheme.dimensions.thirtyTwo))

        CustomButton(text = "Login", onClick = { onAction(UiAction.LoginClicked) })
        Spacer(modifier = Modifier.height(LMTheme.dimensions.sixteen))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LMTheme.dimensions.sixteen),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CustomDivider(
                modifier = Modifier.weight(1f),
                thickness = LMTheme.dimensions.one,
                color = LMTheme.colors.primary
            )

            Spacer(modifier = Modifier.width(LMTheme.dimensions.eight))

            Text(
                text = stringResource(id = R.string.or_login_with_text),
                fontSize = LMTheme.typography.medium,
                color = LMTheme.colors.black
            )

            Spacer(modifier = Modifier.width(LMTheme.dimensions.eight))

            CustomDivider(
                modifier = Modifier.weight(1f),
                thickness = LMTheme.dimensions.one,
                color = LMTheme.colors.primary
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = LMTheme.dimensions.sixteen),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomIconButton(
                iconResId = R.drawable.ic_google,
                onClickAction = {},
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(
    @PreviewParameter(LoginScreenPreviewProvider::class) uiState: UiState,
) {
    LoginScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        onNavigateToHome = {},
        onNavigateToForgotPassword = {},
        onNavigateToBack = {},
    )
}