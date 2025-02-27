package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.core.domain.PhoneNumberVisualTransformation
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme

@Composable
fun FMPhoneTextField(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null
) {

    FMOutlineTextField(
        value = phoneNumber,
        onValueChange = {
            val digitsOnly = it.filter { char -> char.isDigit() }
            if (digitsOnly.length <= 10) {
                onPhoneNumberChange(digitsOnly)
            }
        },
        label = "Phone",
        isError = isError,
        errorMessage = errorMessage,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = PhoneNumberVisualTransformation(),
        modifier = modifier
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FMPhoneTextFieldPreview() {
    FMTheme {
        FMPhoneTextField(
            phoneNumber = "5438615761",
            onPhoneNumberChange = {}
        )
    }
}
