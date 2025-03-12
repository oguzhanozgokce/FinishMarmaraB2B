package com.oguzhanozgokce.finishmarmarab2b.core.domain.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

private const val MAX_EXPIRATION_DATE_LENGTH = 4
private const val EXPIRATION_DATE_DIVIDER_INDEX = 2
private const val EXPIRATION_DATE_DIVIDER = "/"
private const val ONE = 1
private const val MIN_OFFSET = 0

object VisualTransformations {
    val expirationDateTransformation = VisualTransformation { text ->
        val digits = text.text.filter { it.isDigit() }.take(MAX_EXPIRATION_DATE_LENGTH)

        val formattedText = buildString {
            for (i in digits.indices) {
                if (i == EXPIRATION_DATE_DIVIDER_INDEX) append(EXPIRATION_DATE_DIVIDER)
                append(digits[i])
            }
        }

        val transformedText = AnnotatedString(formattedText)

        TransformedText(
            transformedText,
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val originalLength = digits.length
                    val clampedOffset = offset.coerceIn(MIN_OFFSET, originalLength)

                    val transformedOffset = if (clampedOffset <= EXPIRATION_DATE_DIVIDER_INDEX) {
                        clampedOffset
                    } else {
                        clampedOffset + ONE
                    }

                    return transformedOffset.coerceIn(MIN_OFFSET, formattedText.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val transformedLength = formattedText.length
                    val clampedOffset = offset.coerceIn(MIN_OFFSET, transformedLength)

                    return if (clampedOffset <= EXPIRATION_DATE_DIVIDER_INDEX) {
                        clampedOffset
                    } else {
                        clampedOffset - ONE
                    }.coerceIn(MIN_OFFSET, digits.length)
                }
            }
        )
    }
}
