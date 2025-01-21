package com.oguzhanozgokce.finishmarmarab2b.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.cachedIn
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.fold
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetProductCommentsUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetProductDetailUseCase
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product.GetProductQuestionsAndAnswersUseCase
import com.oguzhanozgokce.finishmarmarab2b.navigation.Screen
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCommentsUseCase: GetProductCommentsUseCase,
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val getQuestionsAndAnswersUseCase: GetProductQuestionsAndAnswersUseCase
) : MVI<UiState, UiEffect, UiAction>(UiState()) {

    private val args = savedStateHandle.toRoute<Screen.Detail>()

    init {
        fetchProductDetail(args.id)
        fetchComments(args.id)
        fetchQuestionsAndAnswers(args.id)
    }

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.FetchProductDetail -> fetchProductDetail(args.id)
            is UiAction.FetchComments -> fetchComments(args.id)
            is UiAction.FetchQuestionsAndAnswers -> fetchQuestionsAndAnswers(args.id)
        }
    }
    private fun fetchComments(productId: Int) {
        updateState { copy(isLoading = true) }
        val flow = getCommentsUseCase(productId)
            .cachedIn(viewModelScope)
        updateState { copy(comments = flow, isLoading = false) }
    }
    private fun fetchProductDetail(productId: Int) {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            val result = getProductDetailUseCase(productId)
            result.fold(
                onSuccess = {
                    updateState { copy(product = it, isLoading = false) }
                },
                onError = {
                    updateState { copy(error = it, isLoading = false) }
                    emitUiEffect(UiEffect.ShowToast(it))
                }
            )
        }
    }

    private fun fetchQuestionsAndAnswers(productId: Int) {
        updateState { copy(isLoading = true) }
        val flow = getQuestionsAndAnswersUseCase(productId)
            .cachedIn(viewModelScope)
        updateState { copy(questionsAndAnswers = flow, isLoading = false) }
    }
}