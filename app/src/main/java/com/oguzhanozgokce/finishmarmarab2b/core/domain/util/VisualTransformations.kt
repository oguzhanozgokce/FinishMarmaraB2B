package com.oguzhanozgokce.finishmarmarab2b.core.domain.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object VisualTransformations {
    val expirationDateTransformation = VisualTransformation { text ->
        val digits = text.text.filter { it.isDigit() }.take(4)

        val formattedText = buildString {
            for (i in digits.indices) {
                if (i == 2) append("/")
                append(digits[i])
            }
        }

        val transformedText = AnnotatedString(formattedText)

        TransformedText(
            transformedText,
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val originalLength = digits.length
                    val clampedOffset = offset.coerceIn(0, originalLength)

                    val transformedOffset = if (clampedOffset <= 2) {
                        clampedOffset
                    } else {
                        clampedOffset + 1
                    }

                    return transformedOffset.coerceIn(0, formattedText.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val transformedLength = formattedText.length
                    val clampedOffset = offset.coerceIn(0, transformedLength)

                    return if (clampedOffset <= 2) {
                        clampedOffset
                    } else {
                        clampedOffset - 1
                    }.coerceIn(0, digits.length)
                }
            }
        )
    }
}

