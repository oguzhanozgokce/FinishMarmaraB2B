package com.oguzhanozgokce.finishmarmarab2b.ui.editprofile

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User

object EditProfileContract {
    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val name: String = "",
        val surname: String = "",
        val email: String = "",
        val phoneNumber: String = "",
        val birthDate: String = "",
        val user: User = User(),
        val selectedDate: String = "",
        val error: String = ""
    )

    sealed class UiAction {
        data class OnChangeName(val name: String) : UiAction()
        data class OnChangeSurname(val surname: String) : UiAction()
        data class OnChangeEmail(val email: String) : UiAction()
        data class OnChangePhoneNumber(val phoneNumber: String) : UiAction()
        data class OnChangeBirthDate(val birthDate: String) : UiAction()
        data object SaveClick : UiAction()
    }

    sealed class UiEffect {
        data class ShowToast(val error: String) : UiEffect()
    }
}
