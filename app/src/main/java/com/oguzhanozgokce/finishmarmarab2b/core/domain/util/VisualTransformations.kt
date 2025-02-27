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

        TransformedText(
            AnnotatedString(formattedText),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int =
                    if (offset <= 2) offset else offset + 1

                override fun transformedToOriginal(offset: Int): Int =
                    if (offset <= 2) offset else offset - 1
            }
        )
    }

}
