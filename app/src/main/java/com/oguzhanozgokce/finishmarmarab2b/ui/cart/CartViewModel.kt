package com.oguzhanozgokce.finishmarmarab2b.ui.cart

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.DeleteBasketAllUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.DeleteBasketProductUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetBasketProductsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.PostProductBasketUseCase
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
    private val postProductBasketUseCase: PostProductBasketUseCase
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

    private fun getBasketProducts(isLoading: Boolean = false) {
        updateState { copy(isLoading = isLoading) }
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
                },
                onError = { error ->
                    updateState { copy(isLoading = false) }
                    emitUiEffect(UiEffect.ShowToast(error))
                    Log.e("GetBasketProducts", "Error: $error")
                }
            )
        }
    }

    private fun deleteBasketProduct(productId: Int) {
        viewModelScope.launch {
            deleteBasketProductUseCase(productId).fold(
                onSuccess = {
                    emitUiEffect(UiEffect.ShowToast("Product deleted from basket"))
                },
                onError = { error ->
                    emitUiEffect(UiEffect.ShowToast(error))
                }
            )
        }
    }

    private fun deleteBasketAll() {
        viewModelScope.launch {
            deleteBasketAllUseCase().fold(
                onSuccess = {
                    emitUiEffect(UiEffect.ShowToast("All products deleted from basket"))
                },
                onError = { error ->
                    emitUiEffect(UiEffect.ShowToast(error))
                    Log.e("DeleteBasketAll", "Error: $error")
                }
            )
        }
    }

    private fun postProductBasket(productId: Int) {
        viewModelScope.launch {
            postProductBasketUseCase(productId).fold(
                onSuccess = {
                    emitUiEffect(UiEffect.ShowToast("Product added to basket"))
                },
                onError = {
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
