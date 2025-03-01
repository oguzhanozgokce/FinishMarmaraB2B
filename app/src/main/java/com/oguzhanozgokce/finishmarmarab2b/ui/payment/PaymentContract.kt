package com.oguzhanozgokce.finishmarmarab2b.ui.payment

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Address
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Province

object PaymentContract {
    data class UiState(
        val isLoading: Boolean = false,
        val products: List<Product> = emptyList(),
        val totalPrice: Double = 0.0,
        val showDialog: Boolean = false,
        val cities: List<Province> = emptyList(),
        val districts: List<String> = emptyList(),
        val addressList: List<Address> = emptyList(),
        val errorMessage: String? = null,
        val cardNumber: String = "",
        val expirationDateValue: String = "",
        val cvv: String = "",
        val cardName: String = "",
    )

    sealed class UiAction {
        data object HideDialog : UiAction()
        data object ShowDialog : UiAction()
        data class OnChangeCardNumber(val cardNumber: String) : UiAction()
        data class OnChangeExpirationDate(val expirationDateValue: String) : UiAction()
        data class OnChangeCvv(val cvv: String) : UiAction()
        data class OnChangeCardName(val cardName: String) : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
