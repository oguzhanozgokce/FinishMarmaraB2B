package com.oguzhanozgokce.finishmarmarab2b.core.domain

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }.take(10)
        val transformedText = formatPhoneNumberMask(digits)

        return TransformedText(
            AnnotatedString(transformedText),
            PhoneNumberOffsetMapping(digits.length)
        )
    }
}

fun formatPhoneNumberMask(input: String): String {
    val trimmed = input.take(10)
    return buildString {
        append("0 (")
        repeat(3) { i -> append(trimmed.getOrNull(i) ?: ' ') }
        append(") ")
        repeat(3) { i -> append(trimmed.getOrNull(i + 3) ?: ' ') }
        append(" ")
        repeat(2) { i -> append(trimmed.getOrNull(i + 6) ?: ' ') }
        append(" ")
        repeat(2) { i -> append(trimmed.getOrNull(i + 8) ?: ' ') }
    }
}
