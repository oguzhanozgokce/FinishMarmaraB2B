package com.oguzhanozgokce.finishmarmarab2b.ui.login

object LoginContract {
    data class UiState(
        val isLoading: Boolean = false,
        val email: String = "",
        val password: String = "",
        val error: String = "",
        val list: List<String> = emptyList(),
    )

    sealed class UiAction{
        data class EmailChanged(val email: String) : UiAction()
        data class PasswordChanged(val password: String) : UiAction()
        data object LoginClicked : UiAction()
        data object ClearError : UiAction()
    }

    sealed class UiEffect{
        data object ShowAlertDialog : UiEffect()
        data object GoToForgotPassword : UiEffect()
        data object GoToBack : UiEffect()
        data object GoToHome : UiEffect()
        data class ShowToast(val message: String) : UiEffect()

    }
}