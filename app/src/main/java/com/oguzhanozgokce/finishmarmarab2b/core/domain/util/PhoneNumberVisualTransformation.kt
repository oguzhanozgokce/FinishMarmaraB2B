package com.oguzhanozgokce.finishmarmarab2b.core.domain.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

private const val PHONE_NUMBER_MAX_LENGTH = 10
private const val PHONE_AREA_CODE_LENGTH = 3
private const val PHONE_PREFIX_LENGTH = 3
private const val PHONE_NUMBER_INITIAL_INDEX = 6
private const val PHONE_NUMBER_LAST_INDEX = PHONE_NUMBER_INITIAL_INDEX + 2
private const val PHONE_LINE_FIRST_PART_LENGTH = 2
private const val PHONE_LINE_SECOND_PART_LENGTH = 2

class PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }.take(PHONE_NUMBER_MAX_LENGTH)
        val transformedText = formatPhoneNumberMask(digits)

        return TransformedText(
            AnnotatedString(transformedText),
            PhoneNumberOffsetMapping(digits.length)
        )
    }
}

fun formatPhoneNumberMask(input: String): String {
    val trimmed = input.take(PHONE_NUMBER_MAX_LENGTH)
    return buildString {
        append("0 (")
        repeat(PHONE_AREA_CODE_LENGTH) { i -> append(trimmed.getOrNull(i) ?: ' ') }
        append(") ")
        repeat(PHONE_PREFIX_LENGTH) { i ->
            append(
                trimmed.getOrNull(i + PHONE_AREA_CODE_LENGTH) ?: ' '
            )
        }
        append(" ")
        repeat(PHONE_LINE_FIRST_PART_LENGTH) { i ->
            append(
                trimmed.getOrNull(i + PHONE_NUMBER_INITIAL_INDEX) ?: ' '
            )
        }
        append(" ")
        repeat(PHONE_LINE_SECOND_PART_LENGTH) { i ->
            append(
                trimmed.getOrNull(i + PHONE_NUMBER_LAST_INDEX) ?: ' '
            )
        }
    }
}
