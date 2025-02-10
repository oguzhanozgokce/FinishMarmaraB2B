package com.oguzhanozgokce.finishmarmarab2b.ui.payment

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.GetCitiesUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.payment.GetDistrictsForCityUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetBasketProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getBasketProductsUseCase: GetBasketProductsUseCase,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getDistrictsForCityUseCase: GetDistrictsForCityUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        getBasketProducts()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.HideDialog -> hideDialog()
            is UiAction.ShowDialog -> showDialog()
        }
    }

    private fun getBasketProducts() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getBasketProductsUseCase().fold(onSuccess = { basketProducts ->
                updateState {
                    copy(
                        products = basketProducts,
                        totalPrice = basketProducts.sumOf { it.totalPrice },
                        isLoading = false
                    )
                }
            }, onError = { error ->
                updateState { copy(isLoading = false) }
                emitUiEffect(UiEffect.ShowToast(error))
            })
        }
    }

    private fun hideDialog() {
        updateState { copy(showDialog = false) }
    }

    private fun showDialog() {
        updateState { copy(showDialog = true) }
    }

    private fun getCities() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getCitiesUseCase().fold(
                onSuccess = { cities ->
                    updateState { copy(cities = cities, isLoading = false) }
                },
                onError = { error ->
                    updateState { copy(isLoading = false, errorMessage = error) }
                }
            )
        }
    }

    private fun getDistrictsForCity(cityName: String) {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getDistrictsForCityUseCase(cityName).fold(
                onSuccess = { districts ->
                    updateState { copy(districts = districts, isLoading = false) }
                },
                onError = { error ->
                    updateState { copy(isLoading = false, errorMessage = error) }
                }
            )
        }
    }
}
