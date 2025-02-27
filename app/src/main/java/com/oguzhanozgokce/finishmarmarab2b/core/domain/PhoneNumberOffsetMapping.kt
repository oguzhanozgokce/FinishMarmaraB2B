package com.oguzhanozgokce.finishmarmarab2b.core.domain

import androidx.compose.ui.text.input.OffsetMapping

class PhoneNumberOffsetMapping(private val digitsLength: Int) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        return when (offset) {
            0 -> 3
            in 1..3 -> offset + 3
            in 4..6 -> offset + 5
            in 7..8 -> offset + 6
            in 9..10 -> offset + 7
            else -> 17
        }
    }

    override fun transformedToOriginal(offset: Int): Int {
        return when {
            offset < 3 -> 0
            offset in 3..6 -> (offset - 3).coerceAtMost(digitsLength)
            offset in 7..9 -> (offset - 5).coerceAtMost(digitsLength)
            offset in 10..12 -> (offset - 6).coerceAtMost(digitsLength)
            offset >= 13 -> (offset - 7).coerceAtMost(digitsLength)
            else -> digitsLength
        }
    }
}
