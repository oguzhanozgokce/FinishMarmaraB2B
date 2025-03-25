package com.oguzhanozgokce.finishmarmarab2b.core.common.extension

import android.content.Context
import android.widget.Toast
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

private const val PHONE_NUMBER_LENGTH = 10
private const val VISIBLE_PREFIX_LENGTH = 3
private const val VISIBLE_SUFFIX_LENGTH = 2
private const val EXPIRY_DATE_LENGTH = 4
private const val EXPIRY_DATE_MONTH_END = 2
private const val EXPIRY_DATE_YEAR_START = 2
private const val EXPIRY_DATE_YEAR_END = 4

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

fun String?.toLocalDateTimeOrDefaultPerform(
    formatter: DateTimeFormatter,
    defaultDateTime: () -> LocalDateTime = { LocalDateTime.now() }
): LocalDateTime {
    return this?.let {
        runCatching { LocalDateTime.parse(it, formatter) }.getOrDefault(defaultDateTime())
    } ?: defaultDateTime()
}

fun String?.formatDate(): String {
    return this?.toLocalDateTimeOrDefaultPerform(DateTimeFormatter.ISO_DATE_TIME)
        ?.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm")) ?: ""
}

fun String?.toIsoUtcDateString(): String {
    if (this.isNullOrEmpty()) return ""
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDate = LocalDate.parse(this, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .withZone(ZoneOffset.UTC)
    val zonedDateTime = localDate.atStartOfDay(ZoneOffset.UTC)
    return outputFormatter.format(zonedDateTime)
}

fun String?.toLocalizedDisplayDate(): String {
    if (this.isNullOrEmpty()) return ""
    return try {
        val localDate = LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)
        val displayFormatter = DateTimeFormatter
            .ofPattern("dd MMMM yyyy")
            .withLocale(Locale.getDefault())

        localDate.format(displayFormatter)
    } catch (e: Exception) {
        this
    }
}

fun String.toExpirationDateFormat(): String {
    return if (length == EXPIRY_DATE_LENGTH) {
        "${substring(0, EXPIRY_DATE_MONTH_END)}/${
            substring(
                EXPIRY_DATE_YEAR_START,
                EXPIRY_DATE_YEAR_END
            )
        }"
    } else {
        this
    }
}
