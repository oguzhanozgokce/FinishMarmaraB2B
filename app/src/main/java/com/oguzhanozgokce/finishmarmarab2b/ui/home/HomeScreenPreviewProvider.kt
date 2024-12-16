package com.oguzhanozgokce.finishmarmarab2b.ui.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.CategoryUi
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.ProductUi

class HomeScreenPreviewProvider : PreviewParameterProvider<HomeContract.UiState> {
    override val values: Sequence<HomeContract.UiState>
        get() = sequenceOf(
            HomeContract.UiState(
                isLoading = true,
                categoryList = emptyList(),
                productList = emptyList()
            ),
            HomeContract.UiState(
                isLoading = false,
                categoryList = emptyList(),
                productList = emptyList()
            ),
            HomeContract.UiState(
                isLoading = false,
                categoryList = sampleCategoryList,
                productList = sampleProductList
            )
        )
}

internal val sampleCategoryList : List<CategoryUi> = listOf(
    CategoryUi(
        id = 1,
        name = "Electronics",
        image = R.drawable.ic_notification,
    ),
    CategoryUi(
        id = 2,
        name = "Home & Living",
        image = R.drawable.ic_notification,
    ),
    CategoryUi(
        id = 3,
        name = "Clothing",
        image = R.drawable.ic_notification,
    ),
    CategoryUi(
        id = 4,
        name = "Furniture",
        image = R.drawable.ic_notification,
    ),
    CategoryUi(
        id = 5,
        name = "Computer",
        image = R.drawable.ic_notification,
    ),
    CategoryUi(
        id = 6,
        name = "Mont",
        image = R.drawable.ic_notification,
    ),
    CategoryUi(
        id = 7,
        name = "Clothing",
        image = R.drawable.ic_notification,
    ),
)

internal val sampleProductList : List<ProductUi> = listOf(
    ProductUi(
        id = 1,
        name = "Perfume",
        price = "100.00",
        salePrice = "75.00",
        imageUrl = R.drawable.ic_favorite_border,
        description = "A luxurious fragrance.",
        discount = 25,
        rating = 3.0f,
        sellerName = "Seller Name"
    ),
    ProductUi(
        id = 2,
        name = "Sunglasses",
        price = "150.00",
        salePrice = "128.00",
        imageUrl = R.drawable.ic_notification,
        description = "Stylish sunglasses for sunny days.",
        discount = 15,
        rating = 4.5f,
        sellerName = "Seller Name"
    ),
    ProductUi(
        id = 3,
        name = "Smart Watch",
        price = "200.00",
        salePrice = "180.00",
        imageUrl = R.drawable.ic_favorite_border,
        description = "Track your fitness and stay connected.",
        discount = 10,
        rating = 4.0f,
        sellerName = "Seller Name"
    ),
    ProductUi(
        id = 4,
        name = "Backpack",
        price = "80.00",
        salePrice = "64.00",
        imageUrl = R.drawable.ic_notification,
        description = "A durable and spacious backpack.",
        discount = 20,
        rating = 3.5f,
        sellerName = "Seller Name"
    ),
    ProductUi(
        id = 5,
        name = "Headphones",
        price = "120.00",
        salePrice = "96.00",
        imageUrl = R.drawable.ic_favorite_border,
        description = "High-quality sound for your music.",
        discount = 20,
        rating = 4.5f,
        sellerName = "Seller Name"
    )
)