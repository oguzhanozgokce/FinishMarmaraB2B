package com.oguzhanozgokce.finishmarmarab2b.ui.payment

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Location
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Province

object PaymentContract {
    data class UiState(
        val isLoading: Boolean = false,
        val products: List<Product> = emptyList(),
        val totalPrice: Double = 0.0,
        val showDialog: Boolean = false,
        val totalCartCount: Int = 0,
        val cities: List<Province> = emptyList(),
        val districts: List<String> = emptyList(),
        val locationList: List<Location> = emptyList(),
        val selectedLocation: Location? = null,
        val selectedCard: CreditCart? = null,
        val errorMessage: String? = null,
        val cardNumber: String = "",
        val expirationDateValue: String = "",
        val cvv: String = "",
        val cardName: String = "",
        val cardTitle: String = "",
        var isChecked: Boolean = false,
        var isSaveCardChecked: Boolean = false,
        val isShowAgreementDialog: Boolean = false
    )

    sealed class UiAction {
        data object HideDialog : UiAction()
        data object ShowDialog : UiAction()
        data object ShowAgreementDialog : UiAction()
        data object HideAgreementDialog : UiAction()
        data object OnCheckAgreement : UiAction()
        data object OnCheckSaveCard : UiAction()
        data class OnChangeCardTitle(val cardTitle: String) : UiAction()
        data class OnChangeSelectedCard(val creditCart: CreditCart) : UiAction()
        data class OnChangeAddress(val location: Location) : UiAction()
        data class OnChangeCardNumber(val cardNumber: String) : UiAction()
        data class OnChangeExpirationDate(val expirationDateValue: String) : UiAction()
        data class OnChangeCvv(val cvv: String) : UiAction()
        data class OnChangeCardName(val cardName: String) : UiAction()
        data class DeleteLocation(val locationId: Int) : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
