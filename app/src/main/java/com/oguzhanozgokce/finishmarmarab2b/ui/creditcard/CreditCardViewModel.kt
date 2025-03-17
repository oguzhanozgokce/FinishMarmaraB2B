package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.GetCreditCartUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreditCardViewModel @Inject constructor(
    private val getCreditCartUseCase: GetCreditCartUseCase
) : MVI<CreditCardContract.UiState, CreditCardContract.UiEffect, CreditCardContract.UiAction>(
    CreditCardContract.UiState()
) {

    init {
        getCreditCart()
    }

    override fun onAction(uiAction: CreditCardContract.UiAction) {
        super.onAction(uiAction)
    }

    private fun getCreditCart() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getCreditCartUseCase().fold(
                onSuccess = { creditCartList ->
                    updateState { copy(isLoading = false, creditCardList = creditCartList) }
                },
                onError = { error ->
                    updateState { copy(isLoading = false, error = error) }
                }
            )
        }
    }
}
