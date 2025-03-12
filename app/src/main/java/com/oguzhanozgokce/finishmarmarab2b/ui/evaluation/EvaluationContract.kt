package com.oguzhanozgokce.finishmarmarab2b.ui.evaluation

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData

object EvaluationContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val questionAnswer: List<QuestionAnswer> = PreviewMockData.sampleQuestionAnswersData
    )

    sealed class UiAction {
        data class PostProductBasket(val productId: Int) : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
