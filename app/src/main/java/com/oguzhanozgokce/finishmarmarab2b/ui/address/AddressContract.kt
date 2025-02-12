package com.oguzhanozgokce.finishmarmarab2b.ui.address

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Address

object AddressContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val address: Address? = null,
        val addressName: String = "",
        val addressSurname: String = "",
        val addressTel: String = "",
        val province: String = "",
        val city: String = "",
        val openAddress: String = "",
        val addressTitle: String = "",
    )

    sealed class UiAction

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}