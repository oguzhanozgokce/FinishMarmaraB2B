package com.oguzhanozgokce.finishmarmarab2b.ui.splash

import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import com.oguzhanozgokce.finishmarmarab2b.ui.splash.SplashContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.splash.SplashContract.UiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userDataSource: LocalDataSource
) : MVI<Unit, UiEffect, UiAction>(Unit) {

    private val splashShowFlow = MutableStateFlow(true)
    val isSplashShow = splashShowFlow.asStateFlow()

    init {
        viewModelScope.launch {
            splashShowFlow.value = false
            delay(1000)
            checkUserLogin()
        }
    }

    private fun checkUserLogin() = viewModelScope.launch {
        val userId = userDataSource.getUserId()
        if (userId != null) {
            emitUiEffect(UiEffect.NavigateToHome)
        } else {
            emitUiEffect(UiEffect.NavigateToWelcome)
        }
    }
}
