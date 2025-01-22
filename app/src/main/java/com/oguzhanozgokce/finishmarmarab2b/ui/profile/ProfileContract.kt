package com.oguzhanozgokce.finishmarmarab2b.ui.profile

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User

object ProfileContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val user: User = User(),
        val error: String = ""
    )

    sealed class UiAction {
        data object GetUser : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val message: String) : UiEffect()
    }
}
