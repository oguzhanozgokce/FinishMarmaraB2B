package com.oguzhanozgokce.finishmarmarab2b.ui.seller.home

import com.oguzhanozgokce.finishmarmarab2b.core.domain.delegation.MVI
import com.oguzhanozgokce.finishmarmarab2b.ui.seller.home.HomeContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.seller.home.HomeContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.seller.home.HomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : MVI<UiState, UiEffect, UiAction>(UiState())


