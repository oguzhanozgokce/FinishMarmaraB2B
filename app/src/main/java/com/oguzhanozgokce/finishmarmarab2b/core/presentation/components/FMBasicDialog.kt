package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FMBasicDialog(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
    securePolicy: SecureFlagPolicy = SecureFlagPolicy.Inherit
) {
    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = onDismiss,
            modifier = modifier
                .clip(RoundedCornerShape(padding.dimension8))
                .background(colors.white),
            properties = DialogProperties(
                usePlatformDefaultWidth = true,
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                decorFitsSystemWindows = true,
                securePolicy = securePolicy,
            ),
            content = content,
        )
    }
}

@Composable
fun FMConfirmDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
    description: String?,
    confirmText: String,
    dismissText: String,
    buttonFontSize: TextUnit = fontSize.medium
) {
    FMBasicDialog(
        showDialog = showDialog,
        onDismiss = onDismiss,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.white)
                    .padding(
                        top = padding.dimension16,
                        start = padding.dimension16,
                        end = padding.dimension8,
                        bottom = padding.dimension8
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = typography.titleMediumMedium()
                )
                Spacer(modifier = Modifier.height(padding.dimension8))
                description?.let {
                    Text(
                        text = it,
                        style = typography.titleMediumLight().copy(
                            fontSize = fontSize.mediumSmall
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(padding.dimension24))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = padding.dimension16),
                    horizontalArrangement = Arrangement.spacedBy(padding.dimension8),
                ) {
                    FMOutlinedButton(
                        onClick = { onCancel() },
                        text = dismissText,
                        modifier = Modifier.weight(1f),
                        fontSize = buttonFontSize,
                        height = 44.dp,
                    )
                    FMButton(
                        onClick = onConfirm,
                        text = confirmText,
                        modifier = Modifier.weight(1f),
                        height = 44.dp,
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun FMConfirmDialogPreview() {
    FMTheme {
        FMConfirmDialog(
            showDialog = true,
            onDismiss = {},
            onCancel = {},
            onConfirm = {},
            title = "Do you want to cancel the order?",
            description = "Description",
            confirmText = "Confirm",
            dismissText = "Dismiss"
        )
    }
}
