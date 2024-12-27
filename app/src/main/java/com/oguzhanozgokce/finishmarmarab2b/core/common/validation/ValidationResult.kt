package com.oguzhanozgokce.finishmarmarab2b.core.common.validation

sealed class ValidationResult {
    data object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}
