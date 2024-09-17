package com.oguzhanozgokce.finishmarmarab2b.ui.signup

object SignupContract {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val name: String = "",
        val surname: String = "",
        val email: String = "",
        val password: String = "",
        val list: List<String> = emptyList(),
    )

    sealed class UiAction{
        data class NameChanged(val name: String): UiAction()
        data class SurnameChanged(val surname: String): UiAction()
        data class EmailChanged(val email: String): UiAction()
        data class PasswordChanged(val password: String): UiAction()
        data object Signup: UiAction()
        data object ClearError: UiAction()
    }

    sealed class UiEffect {
        data class ShowAlertDialog(val message: String): UiEffect()
        data object GoToHome: UiEffect()
        data object GoToBack: UiEffect()
    }
}