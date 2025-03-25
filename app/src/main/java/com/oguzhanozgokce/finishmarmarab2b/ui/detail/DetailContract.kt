package com.oguzhanozgokce.finishmarmarab2b.ui.detail

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.UserComment
import kotlinx.coroutines.flow.Flow

object DetailContract {

    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val product: Product? = null,
        val questionsAndAnswers: Flow<PagingData<QuestionAnswer>>? = null,
        val comments: Flow<PagingData<UserComment>>? = null,
        val error: String? = null
    )

    sealed class UiAction {
        data class FetchProductDetail(val productId: Int) : UiAction()
        data class FetchComments(val productId: Int) : UiAction()
        data class FetchQuestionsAndAnswers(val productId: Int) : UiAction()
        data class ProductBasket(val productId: Int, val productName: String) : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
