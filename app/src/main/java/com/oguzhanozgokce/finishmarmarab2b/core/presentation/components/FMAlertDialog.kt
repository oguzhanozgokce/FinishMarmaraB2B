package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

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
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme

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
                        .size(FMTheme.padding.dimension48)
                        .padding(bottom = FMTheme.padding.dimension8)
                        .align(Alignment.CenterHorizontally)
                )
                Text(text = message)
            }
        },
        confirmButton = {
            FMButton(
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
