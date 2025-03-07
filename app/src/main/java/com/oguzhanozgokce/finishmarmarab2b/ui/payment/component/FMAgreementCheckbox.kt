package com.oguzhanozgokce.finishmarmarab2b.ui.payment.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMAgreementCheckbox(
    isChecked: Boolean,
    onShowDialog: () -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.cardBackground, shape = RoundedCornerShape(padding.dimension8))
            .border(
                padding.dimension1,
                colors.lightGray.copy(alpha = 0.4f),
                shape = RoundedCornerShape(padding.dimension8)
            )
            .padding(horizontal = padding.dimension8, vertical = padding.dimension8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = colors.primary,
                uncheckedColor = colors.text
            )
        )
        Spacer(modifier = Modifier.width(padding.dimension8))
        Text(
            text = buildAnnotatedString {
                val spanText = stringResource(id = R.string.agreement_highlight)
                val fullText = stringResource(id = R.string.agreement_full_text, spanText)
                val startIndex = fullText.indexOf(spanText)
                val endIndex = startIndex + spanText.length
                append(fullText)
                addStyle(
                    style = SpanStyle(color = colors.primary),
                    start = startIndex,
                    end = endIndex
                )
            },
            style = FMTheme.typography.titleSmallMedium(),
            modifier = Modifier
                .weight(1f)
                .padding(end = padding.dimension8)
                .clickable { onShowDialog() }
        )
    }
}

@PreviewLightDark
@Composable
fun FMAgreementCheckboxPreview() {
    FMTheme {
        Column {
            FMAgreementCheckbox(
                isChecked = true,
                onCheckedChange = {},
                onShowDialog = {},
            )
            Spacer(modifier = Modifier.padding(padding.dimension8))
            FMAgreementCheckbox(
                isChecked = false,
                onCheckedChange = {},
                onShowDialog = {},
            )
        }
    }
}
