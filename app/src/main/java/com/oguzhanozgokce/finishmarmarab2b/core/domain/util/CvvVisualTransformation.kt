package com.oguzhanozgokce.finishmarmarab2b.core.domain.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

private const val MAX_CARD_CVV_LENGTH = 3
private const val ZERO = 0

class CvvVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }.take(MAX_CARD_CVV_LENGTH)
        val transformedText = AnnotatedString(digits)

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset.coerceIn(ZERO, digits.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                return offset.coerceIn(ZERO, digits.length)
            }
        }

        return TransformedText(transformedText, offsetMapping)
    }
}
