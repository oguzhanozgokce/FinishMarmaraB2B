package com.oguzhanozgokce.finishmarmarab2b.ui.cart

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.AnalyticsManager
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.DeleteBasketAllUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.DeleteBasketProductUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.GetBasketProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.basket.PostProductBasketUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.cart.CartContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getBasketProductsUseCase: GetBasketProductsUseCase,
    private val deleteBasketProductUseCase: DeleteBasketProductUseCase,
    private val deleteBasketAllUseCase: DeleteBasketAllUseCase,
    private val postProductBasketUseCase: PostProductBasketUseCase,
    private val analyticsManager: AnalyticsManager
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    init {
        getBasketProducts()
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.DeleteBasketProduct -> deleteBasketProduct(uiAction.productId)
            is UiAction.DeleteBasketAll -> deleteBasketAll()
            is UiAction.PostProductBasket -> postProductBasket(uiAction.productId)
            is UiAction.ShowDialog -> showDialog()
            is UiAction.HideDialog -> hideDialog()
        }
    }

    private fun getBasketProducts() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            getBasketProductsUseCase().fold(
                onSuccess = { products ->
                    updateState {
                        copy(
                            basketProducts = products,
                            totalPrice = products.sumOf { it.totalPrice },
                            isLoading = false
                        )
                    }
                    if (products.isNotEmpty()) {
                        analyticsManager.logCartViewed(products)
                    }
                },
                onError = { error ->
                    updateState { copy(isLoading = false) }
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }

    private fun deleteBasketProduct(productId: Int) {
        updateState { copy(topLoading = true) }
        viewModelScope.launch {
            deleteBasketProductUseCase(productId, currentState.basketProducts).fold(
                onSuccess = { updatedList ->
                    updateState {
                        copy(
                            topLoading = false,
                            basketProducts = updatedList,
                            totalPrice = updatedList.sumOf { it.totalPrice },
                        )
                    }
                },
                onError = { error ->
                    updateState { copy(topLoading = false) }
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }

    private fun deleteBasketAll() {
        viewModelScope.launch {
            deleteBasketAllUseCase().fold(
                onSuccess = {
                    emitUiEffect(UiEffect.ShowToast("Your basket is empty"))
                    updateState { copy(basketProducts = emptyList()) }
                },
                onError = { error ->
                    emitUiEffect(UiEffect.ShowToast(error))
                    Log.e("DeleteBasketAll", "Error: $error")
                }
            )
        }
    }

    private fun postProductBasket(productId: Int) {
        updateState { copy(topLoading = true) }
        viewModelScope.launch {
            postProductBasketUseCase(productId).fold(
                onSuccess = { newCount ->
                    updateState {
                        copy(
                            basketProducts = basketProducts.map { product ->
                                if (product.id == productId) {
                                    product.copy(count = newCount)
                                } else {
                                    product
                                }
                            },
                            totalPrice = basketProducts.sumOf { it.totalPrice },
                            topLoading = false
                        )
                    }
                },
                onError = {
                    updateState { copy(topLoading = false) }
                    emitUiEffect(UiEffect.ShowToast(it))
                }
            )
        }
    }

    private fun showDialog() {
        updateState { copy(showDialog = true) }
    }

    private fun hideDialog() {
        updateState { copy(showDialog = false) }
    }
}
