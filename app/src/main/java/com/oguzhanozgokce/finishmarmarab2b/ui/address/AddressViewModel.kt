package com.oguzhanozgokce.finishmarmarab2b.ui.address

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.GetCitiesUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.GetDistrictsForCityUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.address.AddressContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.address.AddressContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.address.AddressContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getDistrictForCityUseCase: GetDistrictsForCityUseCase,
) : MVI<UiState, UiEffect, UiAction>(UiState()) {


    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.ProvinceSelected -> {
                updateState { copy() }
            }

            is UiAction.CitySelected -> {
                updateState { copy(city = uiAction.selectedCity) }
            }

            is UiAction.OnNameChanged -> updateState { copy(addressName = uiAction.name) }
            is UiAction.OnSurnameChanged -> updateState { copy(addressSurname = uiAction.surname) }
            is UiAction.OnOpenAddressChanged -> updateState { copy(openAddress = uiAction.openAddress) }
            is UiAction.OnAddressTitleChanged -> updateState { copy(addressTitle = uiAction.addressTitle) }
            is UiAction.OnPhoneNumberChanged -> updateState { copy(addressTel = uiAction.phoneNumber) }
            is UiAction.LoadProvinces -> loadProvinces()
            is UiAction.LoadCities -> loadProvinces()
        }
    }

    private fun loadProvinces() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getCitiesUseCase().fold(
                onSuccess = { cities ->
                    updateState {
                        copy(
                            provinces = cities,
                            isLoading = false
                        )
                    }
                },
                onError = { error ->
                    updateState { copy(error = error, isLoading = false) }
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }


    private fun loadCityForProvince(provinceName: String) {
        updateState { copy(isLoading = true, cities = emptyList()) }
        viewModelScope.launch {
            getDistrictForCityUseCase(provinceName).fold(
                onSuccess = { districts ->
                    updateState { copy(cities = districts, isLoading = false) }
                },
                onError = { error ->
                    updateState { copy(error = error, isLoading = false) }
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }
}

