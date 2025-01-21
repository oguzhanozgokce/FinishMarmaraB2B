package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.usecase.product

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductQuestionsAndAnswersUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(productId: Int) : Flow<PagingData<QuestionAnswer>> {
        return repository.getProductQuestionsAndAnswers(productId)
    }
}