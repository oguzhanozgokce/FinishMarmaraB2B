package com.oguzhanozgokce.finishmarmarab2b.ui.home

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.CategoryUi
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.ProductUi

object HomeContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val categoryList: List<CategoryUi> = emptyList(),
        val productList: List<ProductUi> = emptyList(),
        val user: User = User(),
        val error: String? = null,
    )

    sealed class UiAction {
        data object LoadGetUser : UiAction()
        data object FetchCategory : UiAction()
        data object FetchProduct : UiAction()
        data object ToggleFavorite : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}