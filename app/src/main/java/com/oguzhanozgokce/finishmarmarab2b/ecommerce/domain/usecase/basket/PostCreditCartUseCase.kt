package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.CreditCartRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.BasketRepository
import javax.inject.Inject

class PostCreditCartUseCase @Inject constructor(
    private val repository: BasketRepository
) {
    suspend operator fun invoke(request: CreditCartRequest): Resource<Unit> {
        return when (val validation = CreditCardValidator.validate(request)) {
            is CreditCardValidationResult.Error -> Resource.Error(validation.error.message)
            is CreditCardValidationResult.Success -> repository.postCreditCart(request)
        }
    }
}

enum class CreditCardValidationError(val message: String) {
    EMPTY_CARD_NAME("Card name cannot be empty"),
    LONG_CARD_NAME("Card name cannot be longer than 25 characters"),
    EMPTY_CARD_NUMBER("Card number cannot be empty"),
    INVALID_CARD_NUMBER("Card number must be 16 digits"),
    EMPTY_EXPIRATION_DATE("Expiration date cannot be empty"),
    EMPTY_CVV("CVV cannot be empty"),
    INVALID_CVV("CVV must be 3 digits"),
    EMPTY_CARD_TITLE("Card title cannot be empty"),
    LONG_CARD_TITLE("Card title cannot be longer than 25 characters")
}

sealed class CreditCardValidationResult {
    data class Success(val creditCartRequest: CreditCartRequest) : CreditCardValidationResult()
    data class Error(val error: CreditCardValidationError) : CreditCardValidationResult()
}

object CreditCardValidator {

    private data class ValidationRule(
        val condition: (CreditCartRequest) -> Boolean,
        val error: CreditCardValidationError
    )

    private val validationRules = listOf(
        ValidationRule(
            { it.cardNameSurname.isNullOrBlank() },
            CreditCardValidationError.EMPTY_CARD_NAME
        ),
        ValidationRule(
            { (it.cardNameSurname?.length ?: 0) > 25 },
            CreditCardValidationError.LONG_CARD_NAME
        ),
        ValidationRule(
            { it.cardNumber.isNullOrBlank() },
            CreditCardValidationError.EMPTY_CARD_NUMBER
        ),
        ValidationRule(
            { it.cardNumber?.length != 16 || it.cardNumber?.all { c -> c.isDigit() } == false },
            CreditCardValidationError.INVALID_CARD_NUMBER
        ),
        ValidationRule(
            { it.lastDate.isNullOrBlank() },
            CreditCardValidationError.EMPTY_EXPIRATION_DATE
        ),
        ValidationRule(
            { it.cardCvv.isNullOrBlank() },
            CreditCardValidationError.EMPTY_CVV
        ),
        ValidationRule(
            { it.cardCvv?.length != 3 || it.cardCvv?.all { c -> c.isDigit() } == false },
            CreditCardValidationError.INVALID_CVV
        ),
        ValidationRule(
            { it.cardTitle.isNullOrBlank() },
            CreditCardValidationError.EMPTY_CARD_TITLE
        ),
        ValidationRule(
            { (it.cardTitle?.length ?: 0) > 25 },
            CreditCardValidationError.LONG_CARD_TITLE
        )
    )

    fun validate(creditCartRequest: CreditCartRequest): CreditCardValidationResult {
        for (rule in validationRules) {
            if (rule.condition(creditCartRequest)) {
                return CreditCardValidationResult.Error(rule.error)
            }
        }
        return CreditCardValidationResult.Success(creditCartRequest)
    }
}
