package com.oguzhanozgokce.finishmarmarab2b.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ui.components.BackIconButton
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomButton
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomDivider
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomIconButton
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomTextField
import com.oguzhanozgokce.finishmarmarab2b.ui.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.login.LoginContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.MB2BTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun LoginScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> LoginContent()
    }
}

@Composable
fun LoginContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MB2BTheme.dimensions.sixteen),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BackIconButton(
            onClick = {},
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(MB2BTheme.dimensions.twenty))
        Text(
            text = stringResource(id = R.string.welcome_login_text),
            fontSize = MB2BTheme.typography.sizeTitle,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(MB2BTheme.dimensions.twelve))
        CustomTextField(
            value = "",
            onValueChange = {},
            label = "Email",
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )
        Spacer(modifier = Modifier.height(MB2BTheme.dimensions.sixteen))
        CustomTextField(
            value = "",
            onValueChange = {},
            label = "Password",
            isPassword = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password"
                )
            },
        )
        Spacer(modifier = Modifier.height(MB2BTheme.dimensions.eight))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_forgot_password),
                contentDescription = null,
                modifier = Modifier.size(MB2BTheme.dimensions.twenty)
            )
            Text(
                modifier = Modifier.clickable { },
                text = stringResource(id = R.string.forgot_password_text),
                fontWeight = FontWeight.Light,
                fontSize = MB2BTheme.typography.small,
                )
        }
        Spacer(modifier = Modifier.height(MB2BTheme.dimensions.thirtyTwo))

        CustomButton(text = "Login", onClick = { })
        Spacer(modifier = Modifier.height(MB2BTheme.dimensions.sixteen))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MB2BTheme.dimensions.sixteen),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CustomDivider(
                modifier = Modifier.weight(1f),
                thickness = MB2BTheme.dimensions.one,
                color = MB2BTheme.colors.primary
            )

            Spacer(modifier = Modifier.width(MB2BTheme.dimensions.eight))

            Text(
                text = stringResource(id = R.string.or_login_with_text),
                fontSize = MB2BTheme.typography.medium,
                color = MB2BTheme.colors.black
            )

            Spacer(modifier = Modifier.width(MB2BTheme.dimensions.eight))

            CustomDivider(
                modifier = Modifier.weight(1f),
                thickness = MB2BTheme.dimensions.one,
                color = MB2BTheme.colors.primary
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MB2BTheme.dimensions.sixteen),
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
    )
}