package com.oguzhanozgokce.finishmarmarab2b.core.common.validation

object InputValidator {

    fun validateEmail(email: String): ValidationResult {
        return if (email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Invalid email format.")
        }
    }

    fun validatePhoneNumber(phone: String): ValidationResult {
        return if (phone.length == 11 && phone.all { it.isDigit() } && phone.startsWith("0")) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Phone number must be 11 digits and numeric.")
        }
    }

    fun validatePassword(password: String): ValidationResult {
        return if (password.length >= 6) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Password must be at least 6 characters long.")
        }
    }

    fun validateName(name: String): ValidationResult {
        return if (name.isNotEmpty()) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Name cannot be empty.")
        }
    }

    fun validateSurname(surname: String): ValidationResult {
        return if (surname.isNotEmpty()) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Surname cannot be empty.")
        }
    }
}
