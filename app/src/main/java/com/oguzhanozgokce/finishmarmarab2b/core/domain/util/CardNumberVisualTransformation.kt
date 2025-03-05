package com.oguzhanozgokce.finishmarmarab2b.core.domain.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object CardNumberVisualTransformation {
    val cardNumberVisualTransformation = VisualTransformation { text ->
        val digits = text.text.filter { it.isDigit() }.take(16)
        val builder = AnnotatedString.Builder()
        val mappingList = mutableListOf<Int>()

        for (i in digits.indices) {
            if (i > 0 && i % 4 == 0) {
                builder.append(' ')
            }
            mappingList.add(builder.length)
            builder.append(digits[i])
        }
        mappingList.add(builder.length)

        val transformedText = builder.toAnnotatedString()

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return mappingList.getOrElse(offset) { transformedText.length }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return mappingList.indexOfLast { it <= offset }
            }
        }
        TransformedText(transformedText, offsetMapping)
    }
}
