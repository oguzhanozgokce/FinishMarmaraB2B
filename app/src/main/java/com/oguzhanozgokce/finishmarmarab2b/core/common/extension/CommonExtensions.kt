package com.oguzhanozgokce.finishmarmarab2b.core.common.extension

import android.content.Context
import android.widget.Toast
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

private const val PHONE_NUMBER_LENGTH = 10
private const val VISIBLE_PREFIX_LENGTH = 3
private const val VISIBLE_SUFFIX_LENGTH = 2

fun Int?.orZero(): Int = this ?: 0
fun Double?.orDoubleZero(): Double = this ?: 0.0
fun String?.orEmpty(): String = this ?: ""
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun String.limitDigits(maxDigits: Int): String {
    return filter { it.isDigit() }.take(maxDigits)
}

fun String.capitalizeFirstLetter(): String {
    return this.lowercase().replaceFirstChar { it.uppercaseChar() }
}

fun User.getFormattedName(): String {
    val formattedSurname = if (surname.isNotEmpty()) "${surname.first()}." else ""
    return "$name $formattedSurname"
}

fun String.formatPhoneNumber(): String {
    val cleanedNumber = this.filter { it.isDigit() }
        .removePrefix("0")

    if (cleanedNumber.length != PHONE_NUMBER_LENGTH) return this

    return "${cleanedNumber.take(VISIBLE_PREFIX_LENGTH)}*****${
        cleanedNumber.takeLast(
            VISIBLE_SUFFIX_LENGTH
        )
    }"
}

fun User.getInitials(): String {
    val nameInitial = name.firstOrNull()?.uppercaseChar() ?: ""
    val surnameInitial = surname.firstOrNull()?.uppercaseChar() ?: ""
    return "$nameInitial$surnameInitial"
}

fun String?.toLocalDateOrDefault(
    formatter: DateTimeFormatter,
    defaultDate: LocalDate = LocalDate.now()
): LocalDate {
    return this?.let {
        try {
            LocalDateTime.parse(it, formatter).toLocalDate()
        } catch (e: DateTimeParseException) {
            defaultDate
        }
    } ?: defaultDate
}

fun String?.toLocalDateTimeOrDefault(
    formatter: DateTimeFormatter,
    defaultDateTime: LocalDateTime = LocalDateTime.now()
): LocalDateTime {
    return this?.let {
        try {
            LocalDateTime.parse(it, formatter)
        } catch (e: DateTimeParseException) {
            defaultDateTime
        }
    } ?: defaultDateTime
}
