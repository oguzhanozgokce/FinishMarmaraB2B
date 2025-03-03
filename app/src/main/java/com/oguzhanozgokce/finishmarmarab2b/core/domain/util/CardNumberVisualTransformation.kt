package com.oguzhanozgokce.finishmarmarab2b.core.domain.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object CardNumberVisualTransformation {
    val cardNumberVisualTransformation = VisualTransformation { text ->
        val digits = text.text.filter { it.isDigit() }.take(16)
        val builder = AnnotatedString.Builder()
        val mapping = mutableListOf<Int>()
        var digitCount = 0

        for (char in digits) {
            if (digitCount > 0 && digitCount % 4 == 0) {
                builder.append(' ')
            }
            if (digitCount < 16) {
                builder.append(char)
                mapping.add(builder.length - 1)
                digitCount++
            }
        }

        val outString = builder.toAnnotatedString()

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return if (offset < mapping.size) {
                    mapping[offset]
                } else {
                    outString.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                val idx = mapping.indexOfLast { it <= offset }
                return if (idx == -1) 0 else idx
            }
        }
        TransformedText(outString, offsetMapping)
    }
}
