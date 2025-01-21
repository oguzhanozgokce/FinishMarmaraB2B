package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

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
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = colors.text,
                            fontWeight = FontWeight.Light
                        )
                    ) {
                        append("Selected Seller ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = colors.primary
                        )
                    ) {
                        append(name)
                    }
                },
                fontSize = fontSize.small,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        }
        TextButton(
            onClick = { onQuestionsAndAnswersClick() }
        ) {
            Text(
                text = "16 Soru & Cevap >",
                fontSize = fontSize.small,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Light,
                color = colors.text
            )
        }
    }
}
