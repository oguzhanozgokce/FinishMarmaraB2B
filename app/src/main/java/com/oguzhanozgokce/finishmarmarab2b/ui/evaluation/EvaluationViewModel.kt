package com.oguzhanozgokce.finishmarmarab2b.ui.evaluation

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.PostProductBasketUseCase
import com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.EvaluationContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.EvaluationContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.EvaluationContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EvaluationViewModel @Inject constructor(
    private val postProductBasketUseCase: PostProductBasketUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.PostProductBasket -> postProductBasket(uiAction.productId)
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
}
