package com.oguzhanozgokce.finishmarmarab2b.ui.splash

object SplashContract {
    sealed class UiAction

    sealed class UiEffect {
        data object NavigateToHome : UiEffect()
        data object NavigateToWelcome : UiEffect()
    }
}
