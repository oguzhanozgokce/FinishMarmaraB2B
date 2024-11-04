package com.oguzhanozgokce.finishmarmarab2b.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.MB2BTheme

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    leadingIcon: @Composable () -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    val containerColor =
        if (isError) MB2BTheme.colors.customButtonColor else MB2BTheme.colors.white
    val indicatorColor =
        if (isError) MB2BTheme.colors.red else MB2BTheme.colors.primary.copy(alpha = 0.3f)

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            leadingIcon = { leadingIcon() },
            label = { Text(label) },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(MB2BTheme.dimensions.eight),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                focusedIndicatorColor = indicatorColor,
                unfocusedIndicatorColor = indicatorColor,
            ),
            isError = isError,
            singleLine = true
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MB2BTheme.colors.red,
                style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(
                    start = MB2BTheme.dimensions.sixteen,
                    top = MB2BTheme.dimensions.four
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CustomTextField(
        value = "",
        onValueChange = {},
        label = "email",
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password") }
    )
}