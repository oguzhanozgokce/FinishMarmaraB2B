package com.oguzhanozgokce.finishmarmarab2b.ui.home

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.CategoryUi
import kotlinx.coroutines.flow.Flow

object HomeContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val categoryList: List<CategoryUi> = emptyList(),
        val productFlow: Flow<PagingData<Product>>? = null,
        val user: User = User(),
        val error: String? = null,
    )

    sealed class UiAction {
        data object LoadGetUser : UiAction()
        data object FetchCategory : UiAction()
        data object FetchProduct : UiAction()
        data class ToggleFavorite(val productId: Int, val isFavorite: Boolean) : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
