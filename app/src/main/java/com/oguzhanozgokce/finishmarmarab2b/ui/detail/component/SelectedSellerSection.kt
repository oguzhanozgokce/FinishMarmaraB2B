package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMAnnotatedText
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun SelectedSellerSection(
    sellerName: String?,
    onQuestionsAndAnswersClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        sellerName?.let { name ->
            FMAnnotatedText(
                prefix = "Selected Seller ",
                highlightedText = name,
                prefixColor = colors.text,
                highlightedColor = colors.primary,
                style = typography.headSmallMedium().copy(
                    fontSize = fontSize.mediumSmall
                ),
                modifier = Modifier
            )
        }
        TextButton(
            onClick = { onQuestionsAndAnswersClick() }
        ) {
            Text(
                text = "16 Soru & Cevap >",
                style = typography.bodySmallLight()
            )
        }
    }
}
