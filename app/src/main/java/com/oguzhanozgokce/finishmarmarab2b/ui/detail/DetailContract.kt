package com.oguzhanozgokce.finishmarmarab2b.ui.detail

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.ProductUi
import kotlinx.serialization.Serializable

object DetailContract {

    data class UiState(
        val isLoading: Boolean = false,
        val list: List<String> = emptyList(),
        val product: ProductUi = ProductUi()
    )

    sealed class UiAction

    sealed class UiEffect
}