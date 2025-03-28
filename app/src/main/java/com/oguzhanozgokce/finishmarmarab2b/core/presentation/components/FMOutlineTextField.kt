package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMOutlineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    isPassword: Boolean = false,
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Text
    ),
    indicatorsColor: Color = colors.primary.copy(alpha = 0.3f),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    readOnly: Boolean = false,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true
) {
    val containerColor =
        if (isError) colors.primary else colors.cardBackground
    val indicatorColor =
        if (isError) colors.red else indicatorsColor

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = readOnly,
            leadingIcon = leadingIcon?.let { { it() } },
            trailingIcon = trailingIcon?.let { { it() } },
            enabled = enabled,
            label = {
                if (label != null) {
                    Text(
                        label,
                        color = if (enabled) colors.text else colors.text.copy(alpha = 0.5f),
                    )
                }
            },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else visualTransformation,
            modifier = Modifier
                .fillMaxWidth()
                .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
            shape = RoundedCornerShape(padding.dimension8),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                focusedIndicatorColor = indicatorColor,
                unfocusedIndicatorColor = indicatorColor,
                focusedTextColor = colors.text,
                unfocusedTextColor = colors.text,
                focusedPlaceholderColor = colors.text,
                unfocusedPlaceholderColor = colors.text,
                disabledPlaceholderColor = colors.text.copy(alpha = 0.5f),
                disabledTextColor = colors.text.copy(alpha = 0.2f),
                disabledContainerColor = containerColor.copy(alpha = 0.5f),
                disabledIndicatorColor = indicatorColor.copy(alpha = 0.2f),
                focusedLabelColor = colors.text,
                unfocusedLabelColor = colors.lightGray,
            ),
            isError = isError,
            singleLine = true,
            keyboardOptions = keyboardOptions
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                style = FMTheme.typography.bodyMediumNormal().copy(
                    color = colors.error
                ),
                modifier = Modifier.padding(
                    start = padding.dimension16,
                    top = padding.dimension4
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
fun Preview() {
    FMTheme {
        FMOutlineTextField(
            value = "",
            onValueChange = {},
            label = "email",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password",
                    tint = colors.onBackground
                )
            }
        )
    }
}
