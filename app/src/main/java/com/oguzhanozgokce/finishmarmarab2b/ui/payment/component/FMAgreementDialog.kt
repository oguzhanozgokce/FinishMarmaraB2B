package com.oguzhanozgokce.finishmarmarab2b.ui.payment.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMBasicDialog
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMAgreementDialog(
    isShowDialog: Boolean,
    onDismiss: () -> Unit
) {
    FMBasicDialog(
        isShowDialog = isShowDialog,
        onDismiss = onDismiss,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding.dimension16)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.agreement_dialog_title),
                    style = FMTheme.typography.titleMediumSemiBold().copy(fontSize = fontSize.body),
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(padding.dimension8))
                Text(
                    text = stringResource(id = R.string.agreement_dialog_content),
                    style = FMTheme.typography.titleMediumMedium()
                        .copy(fontSize = fontSize.mediumSmall),
                )
                Spacer(modifier = Modifier.height(padding.dimension16))
                FMButton(
                    text = stringResource(id = R.string.ok),
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.End),
                    height = padding.dimension48,
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AgreementDialogPreview() {
    FMTheme {
        FMAgreementDialog(
            isShowDialog = true,
            onDismiss = {}
        )
    }
}
