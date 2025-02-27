package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

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
import androidx.compose.ui.tooling.preview.Preview
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
    visualTransformation: VisualTransformation? = null,
) {
    val containerColor =
        if (isError) colors.primary else colors.white
    val indicatorColor =
        if (isError) colors.red else indicatorsColor


    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            leadingIcon = leadingIcon?.let {
                { it() }
            },
            trailingIcon = trailingIcon?.let {
                { it() }
            },
            label = {
                if (label != null) {
                    Text(
                        label,
                        color = colors.black
                    )
                }
            },
            visualTransformation = visualTransformation
                ?: if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(padding.dimension8),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                focusedIndicatorColor = indicatorColor,
                unfocusedIndicatorColor = indicatorColor,
                focusedTextColor = colors.black,
                unfocusedTextColor = colors.black,
                focusedPlaceholderColor = colors.black,
                unfocusedPlaceholderColor = colors.black,
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

@Preview(showBackground = true)
@Composable
fun Preview() {
    FMOutlineTextField(
        value = "",
        onValueChange = {},
        label = "email",
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password") }
    )
}
