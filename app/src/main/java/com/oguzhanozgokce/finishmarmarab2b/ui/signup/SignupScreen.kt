package com.oguzhanozgokce.finishmarmarab2b.ui.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.BackIconButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.CustomAlertDialog
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.CustomTextField
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.navigation.SignUpNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SignupScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    signupNavActions: SignUpNavActions
) {
    var alertDialogState by remember { mutableStateOf(false) }
    val context = LocalContext.current
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> SignupContent(
            uiState = uiState,
            onAction = onAction,
            signupNavActions = signupNavActions
        )
    }
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowToast -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            is UiEffect.ShowAlertDialog -> alertDialogState = true
            is UiEffect.GoToHome -> signupNavActions.navigateToHome()
            is UiEffect.GoToBack -> signupNavActions.navigateToBack()
        }
    }

    if (alertDialogState) {
        CustomAlertDialog(
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
fun SignupContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    signupNavActions: SignUpNavActions
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding.dimension16),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            BackIconButton(
                onClick = { signupNavActions.navigateToBack() },
                modifier = Modifier
                    .padding(vertical = padding.dimension8)
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(padding.dimension20))
            Text(
                text = stringResource(id = R.string.hello_register_text),
                modifier = Modifier.align(Alignment.Start),
                style = typography.headExtraLargeBold(),
            )
            Spacer(modifier = Modifier.height(padding.dimension32))
            CustomTextField(
                value = uiState.name,
                onValueChange = { onAction(UiAction.NameChanged(it)) },
                label = "Name",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Name"
                    )
                },
                modifier = Modifier.padding(vertical = padding.dimension4)
            )
            Spacer(modifier = Modifier.height(padding.dimension4))
            CustomTextField(
                value = uiState.surname,
                onValueChange = { onAction(UiAction.SurnameChanged(it)) },
                label = "Surname",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Surname"
                    )
                },
                modifier = Modifier.padding(vertical = padding.dimension4)
            )
            Spacer(modifier = Modifier.height(padding.dimension4))
            CustomTextField(
                value = uiState.email,
                onValueChange = { onAction(UiAction.EmailChanged(it)) },
                label = "Email",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email"
                    )
                },
                modifier = Modifier.padding(vertical = padding.dimension4)
            )
            Spacer(modifier = Modifier.height(padding.dimension4))
            CustomTextField(
                value = uiState.password,
                onValueChange = { onAction(UiAction.PasswordChanged(it)) },
                label = "Password",
                isPassword = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Password"
                    )
                },
                modifier = Modifier.padding(vertical = padding.dimension4)
            )
            Spacer(modifier = Modifier.height(padding.dimension4))
            CustomTextField(
                value = uiState.phoneNumber,
                onValueChange = { onAction(UiAction.PhoneNumberChanged(it)) },
                label = "Phone",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Phone number"
                    )
                },
                modifier = Modifier.padding(vertical = padding.dimension4)
            )
            Spacer(modifier = Modifier.height(padding.dimension32))
            FMButton(text = "Register", onClick = { onAction(UiAction.Signup) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview(
    @PreviewParameter(SignupScreenPreviewProvider::class) uiState: UiState,
) {
    SignupScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        signupNavActions = SignUpNavActions(
            navigateToHome = {},
            navigateToBack = {}
        )
    )
}