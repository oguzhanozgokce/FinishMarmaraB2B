package com.oguzhanozgokce.finishmarmarab2b.core.domain.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

private const val MAX_CARD_NUMBER_LENGTH = 16
private const val CARD_NUMBER_GROUP_SIZE = 4
private const val SPACE_CHARACTER = ' '
private const val ZERO = 0

object CardNumberVisualTransformation {
    val cardNumberVisualTransformation = VisualTransformation { text ->
        val digits = text.text.filter { it.isDigit() }.take(MAX_CARD_NUMBER_LENGTH)
        val builder = AnnotatedString.Builder()
        val mappingList = mutableListOf<Int>()

        for (i in digits.indices) {
            if (i > ZERO && i % CARD_NUMBER_GROUP_SIZE == ZERO) {
                builder.append(SPACE_CHARACTER)
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
