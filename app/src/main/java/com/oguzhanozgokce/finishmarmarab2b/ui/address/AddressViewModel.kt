package com.oguzhanozgokce.finishmarmarab2b.ui.address

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SaveLocationRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Location
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.GetCitiesUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.GetDistrictsForCityUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.PostSaveLocationUseCase
import com.oguzhanozgokce.finishmarmarab2b.navigation.Address
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
    private val saveLocationUseCase: PostSaveLocationUseCase,
    savedStateHandle: SavedStateHandle
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    private val args = Address.from(savedStateHandle)
    private val location = args.location

    init {
        updateUiStateWithLocation(location)
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.ProvinceSelected -> {
                updateState {
                    copy(
                        selectedProvince = uiAction.selectedProvince,
                        selectedCity = "",
                        cities = emptyList(),
                        province = uiAction.selectedProvince.name
                    )
                }
                loadCityForProvince(uiAction.selectedProvince.name)
            }

            is UiAction.CitySelected -> updateState { copy(selectedCity = uiAction.selectedCity) }
            is UiAction.OnNameChanged -> updateState { copy(addressName = uiAction.name) }
            is UiAction.OnSurnameChanged -> updateState { copy(addressSurname = uiAction.surname) }
            is UiAction.OnOpenAddressChanged -> updateState { copy(openAddress = uiAction.openAddress) }
            is UiAction.OnAddressTitleChanged -> updateState { copy(addressTitle = uiAction.addressTitle) }
            is UiAction.OnPhoneNumberChanged -> updateState { copy(addressTel = uiAction.phoneNumber) }
            is UiAction.LoadProvinces -> loadProvinces()
            is UiAction.LoadCities -> loadProvinces()
            is UiAction.SaveAddress -> saveAddress()
        }
    }

    private fun updateUiStateWithLocation(location: Location) {
        updateState {
            copy(
                province = location.province,
                selectedCity = location.city,
                openAddress = location.openAddress,
                addressTitle = location.addressTitle,
                addressTel = location.addressTel,
                addressName = location.nameSurname.substringBefore(" "),
                addressSurname = location.nameSurname.substringAfter(" ", "")
            )
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

    private fun saveAddress() {
        val address = SaveLocationRequest(
            province = currentState.selectedProvince?.name,
            city = currentState.selectedCity,
            openAddress = currentState.openAddress,
            addressTitle = currentState.addressTitle,
            addressTel = currentState.addressTel,
            nameSurname = "${currentState.addressName} ${currentState.addressSurname}"
        )
        viewModelScope.launch {
            saveLocationUseCase(address).fold(
                onSuccess = {
                    emitUiEffect(UiEffect.ShowToast("Success"))
                    resetState()
                    emitUiEffect(UiEffect.NavigateToPayment)
                },
                onError = { error ->
                    updateState { copy(error = error) }
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }

    private fun resetState() {
        updateState {
            copy(
                selectedProvince = null,
                selectedCity = "",
                cities = emptyList(),
                addressName = "",
                addressSurname = "",
                openAddress = "",
                addressTitle = "",
                addressTel = "",
            )
        }
    }
}
