package com.oguzhanozgokce.finishmarmarab2b.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.MB2BTheme

@Composable
fun CustomAlertDialog(
    errorMessage: String,
    onDismiss: () -> Unit,
    confirmButtonClickListener: (() -> Unit)? = null
) {
    val message = when (errorMessage) {
        "email_format_error" -> stringResource(id = R.string.email_format_error)
        "password_invalid_error" -> stringResource(id = R.string.password_invalid_error)
        "empty_field_error" -> stringResource(id = R.string.empty_field_error)
        else -> errorMessage
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(id = R.string.validation_error)) },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = "Error Icon",
                    modifier = Modifier
                        .size(MB2BTheme.dimensions.fortyEight)
                        .padding(bottom = MB2BTheme.dimensions.eight)
                        .align(Alignment.CenterHorizontally)
                )
                Text(text = message)
            }
        },
        confirmButton = {
            CustomButton(
                text = stringResource(id = R.string.ok_button),
                onClick = {
                    confirmButtonClickListener?.invoke()
                    onDismiss()
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomAlertDialogPreview() {
    CustomAlertDialog(
        errorMessage = "email_format_error",
        onDismiss = {},
        confirmButtonClickListener = {}
    )
}
