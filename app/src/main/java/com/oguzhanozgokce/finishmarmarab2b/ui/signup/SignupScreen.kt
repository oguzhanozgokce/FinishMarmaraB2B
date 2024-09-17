package com.oguzhanozgokce.finishmarmarab2b.ui.signup

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.common.collectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.ui.components.BackIconButton
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomAlertDialog
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomButton
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomTextField
import com.oguzhanozgokce.finishmarmarab2b.ui.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.signup.SignupContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.MB2BTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SignupScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToBack: () -> Unit,
) {
    var alertDialogState by remember { mutableStateOf(false) }

    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> SignupContent(
            uiState = uiState,
            onAction = onAction,
            onNavigateToBack = onNavigateToBack
        )
    }
    uiEffect.collectWithLifecycle { uiEffect ->
        when (uiEffect) {
            is UiEffect.ShowAlertDialog -> alertDialogState = true
            is UiEffect.GoToHome -> onNavigateToHome()
            is UiEffect.GoToBack -> onNavigateToBack()
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
    onNavigateToBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MB2BTheme.dimensions.sixteen),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ){
            BackIconButton(
                onClick = {onNavigateToBack()},
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(MB2BTheme.dimensions.twenty))
            Text(
                text = stringResource(id = R.string.hello_register_text),
                fontWeight = FontWeight.Bold,
                fontSize = MB2BTheme.typography.sizeTitle,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(MB2BTheme.dimensions.twelve))
            CustomTextField(
                value = uiState.name,
                onValueChange = {onAction(UiAction.NameChanged(it))},
                label = "Name",
                leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Name") }
            )
            Spacer(modifier = Modifier.height(MB2BTheme.dimensions.four))
            CustomTextField(
                value = uiState.surname,
                onValueChange = {onAction(UiAction.SurnameChanged(it))},
                label = "Surname",
                leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Surname") }
            )
            Spacer(modifier = Modifier.height(MB2BTheme.dimensions.four))
            CustomTextField(
                value = uiState.email,
                onValueChange = {onAction(UiAction.EmailChanged(it))},
                label = "Email",
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
            )
            Spacer(modifier = Modifier.height(MB2BTheme.dimensions.four))
            CustomTextField(
                value = uiState.password,
                onValueChange = {onAction(UiAction.PasswordChanged(it))},
                label = "Password",
                isPassword = true,
                leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Password") }
            )
            Spacer(modifier = Modifier.height(MB2BTheme.dimensions.thirtyTwo))
            CustomButton(text = "Register", onClick = { })
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
        onNavigateToHome = {},
        onNavigateToBack = {},
    )
}