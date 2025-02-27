package com.oguzhanozgokce.finishmarmarab2b.ui.address

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Province

object AddressContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val addressName: String = "",
        val addressSurname: String = "",
        val addressTel: String = "",
        val province: String = "",
        val city: String = "",
        val openAddress: String = "",
        val addressTitle: String = "",
        val provinces: List<Province> = emptyList(),
        val cities: List<String> = emptyList(),
        val selectedProvince: Province? = null,
        val selectedCity: String = "",
        val error: String = ""
    )

    sealed class UiAction {
        data class ProvinceSelected(val selectedProvince: Province) : UiAction()
        data class CitySelected(val selectedCity: String) : UiAction()
        data class OnNameChanged(val name: String) : UiAction()
        data class OnSurnameChanged(val surname: String) : UiAction()
        data class OnOpenAddressChanged(val openAddress: String) : UiAction()
        data class OnAddressTitleChanged(val addressTitle: String) : UiAction()
        data class OnPhoneNumberChanged(val phoneNumber: String) : UiAction()
        data object LoadProvinces : UiAction()
        data object LoadCities : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}