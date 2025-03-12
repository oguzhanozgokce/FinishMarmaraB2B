package com.oguzhanozgokce.finishmarmarab2b.core.domain.util

import androidx.compose.ui.text.input.OffsetMapping

private const val ZERO = 0
private const val ONE = 1
private const val THREE = 3
private const val FOUR = 4
private const val FIVE = 5
private const val SIX = 6
private const val SEVEN = 7
private const val EIGHT = 8
private const val NINE = 9
private const val TEN = 10
private const val TWELVE = 12
private const val THIRTEEN = 13
private const val SEVENTEEN = 17

class PhoneNumberOffsetMapping(private val digitsLength: Int) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        return when (offset) {
            ZERO -> THREE
            in ONE..THREE -> offset + THREE
            in FOUR..SIX -> offset + FIVE
            in SEVEN..EIGHT -> offset + SIX
            in NINE..TEN -> offset + SEVEN
            else -> SEVENTEEN
        }
    }

    override fun transformedToOriginal(offset: Int): Int {
        return when {
            offset < THREE -> ZERO
            offset in THREE..SIX -> (offset - THREE).coerceAtMost(digitsLength)
            offset in SEVEN..NINE -> (offset - FIVE).coerceAtMost(digitsLength)
            offset in TEN..TWELVE -> (offset - SIX).coerceAtMost(digitsLength)
            offset >= THIRTEEN -> (offset - SEVEN).coerceAtMost(digitsLength)
            else -> digitsLength
        }
    }
}
