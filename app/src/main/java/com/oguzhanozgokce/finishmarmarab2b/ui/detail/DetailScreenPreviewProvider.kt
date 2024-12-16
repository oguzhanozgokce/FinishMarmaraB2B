package com.oguzhanozgokce.finishmarmarab2b.ui.detail

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.ProductUi

class DetailScreenPreviewProvider : PreviewParameterProvider<DetailContract.UiState> {
    override val values: Sequence<DetailContract.UiState>
        get() = sequenceOf(
            DetailContract.UiState(
                isLoading = true,
                product = ProductUi(),
            ),
            DetailContract.UiState(
                isLoading = false,
                product = ProductUi()
            ),
            DetailContract.UiState(
                isLoading = false,
                product = ProductUi(
                    id = 1,
                    name = "Sample Product",
                    price = "$100",
                    imageUrl = R.drawable.image_product, // Örnek görsel
                    description = "This is a sample product description.",
                    discount = 10,
                    salePrice = "$90",
                    rating = 4.5f,
                    sellerName = "Sample Seller"
                )
            )
        )
}