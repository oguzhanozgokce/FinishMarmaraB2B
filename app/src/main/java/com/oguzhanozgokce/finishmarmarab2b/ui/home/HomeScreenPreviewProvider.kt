package com.oguzhanozgokce.finishmarmarab2b.ui.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Image
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Seller
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.CategoryUi
import java.time.LocalDateTime

class HomeScreenPreviewProvider : PreviewParameterProvider<HomeContract.UiState> {
    override val values: Sequence<HomeContract.UiState>
        get() = sequenceOf(
            HomeContract.UiState(
                isLoading = true,
                categoryList = emptyList(),
            ),
            HomeContract.UiState(
                isLoading = false,
                categoryList = emptyList(),
            ),
            HomeContract.UiState(
                isLoading = false,
                categoryList = sampleCategoryList,
            )
        )
}

internal val sampleCategoryList: List<CategoryUi> = listOf(
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

internal val sampleProductList: List<Product> = listOf(
    Product(
        id = 1,
        title = "Wireless Mouse",
        description = "Ergonomic wireless mouse with adjustable DPI settings.",
        price = 25.99,
        discountedPrice = 23.99,
        sellerId = 1,
        stock = 150,
        rate = 4.5,
        commentCount = 35,
        categoryId = 101,
        createdAt = LocalDateTime.now().minusDays(5),
        category = Category(
            id = 101,
            name = "Electronics",
            categoryImage = "https://example.com/images/electronics.jpg"
        ),
        seller = Seller(
            id = 1,
            name = "TechStore"
        ),
        images = listOf(
            Image(imageUrl = "https://example.com/images/mouse1.jpg"),
            Image(imageUrl = "https://example.com/images/mouse2.jpg")
        )
    ),
    Product(
        id = 2,
        title = "Gaming Keyboard",
        description = "Mechanical keyboard with RGB lighting and customizable keys.",
        price = 75.49,
        discountedPrice = 70.99,
        sellerId = 2,
        stock = 200,
        rate = 4.8,
        commentCount = 50,
        categoryId = 101,
        createdAt = LocalDateTime.now().minusDays(10),
        category = Category(
            id = 101,
            name = "Electronics",
            categoryImage = "https://example.com/images/electronics.jpg"
        ),
        seller = Seller(
            id = 2,
            name = "GameZone"
        ),
        images = listOf(
            Image(imageUrl = "https://example.com/images/mouse1.jpg"),
            Image(imageUrl = "https://example.com/images/mouse2.jpg")
        )
    ),
    Product(
        id = 3,
        title = "4K Monitor",
        description = "27-inch UHD monitor with HDR support and slim bezels.",
        price = 299.99,
        discountedPrice = 280.99,
        sellerId = 3,
        stock = 50,
        rate = 4.7,
        commentCount = 40,
        categoryId = 101,
        createdAt = LocalDateTime.now().minusDays(20),
        category = Category(
            id = 101,
            name = "Electronics",
            categoryImage = "https://example.com/images/electronics.jpg"
        ),
        seller = Seller(
            id = 3,
            name = "MonitorWorld"
        ),
        images = listOf(
            Image(imageUrl = "https://example.com/images/mouse1.jpg"),
            Image(imageUrl = "https://example.com/images/mouse2.jpg")
        )
    ),
    Product(
        id = 4,
        title = "Noise-Cancelling Headphones",
        description = "Wireless headphones with active noise cancellation.",
        price = 199.99,
        discountedPrice = 189.99,
        sellerId = 4,
        stock = 120,
        rate = 4.6,
        commentCount = 100,
        categoryId = 101,
        createdAt = LocalDateTime.now().minusDays(15),
        category = Category(
            id = 101,
            name = "Electronics",
            categoryImage = "https://example.com/images/electronics.jpg"
        ),
        seller = Seller(
            id = 4,
            name = "AudioTech"
        ),
        images = listOf(
            Image(imageUrl = "https://example.com/images/mouse1.jpg"),
            Image(imageUrl = "https://example.com/images/mouse2.jpg")
        )
    ),
    Product(
        id = 5,
        title = "Smartwatch",
        description = "Waterproof smartwatch with fitness tracking features.",
        price = 129.99,
        discountedPrice = 119.99,
        sellerId = 5,
        stock = 300,
        rate = 4.3,
        commentCount = 20,
        categoryId = 101,
        createdAt = LocalDateTime.now().minusDays(7),
        category = Category(
            id = 101,
            name = "Electronics",
            categoryImage = "https://example.com/images/electronics.jpg"
        ),
        seller = Seller(
            id = 5,
            name = "GadgetHub"
        ),
        images = listOf(
            Image(imageUrl = "https://example.com/images/mouse1.jpg"),
            Image(imageUrl = "https://example.com/images/mouse2.jpg")
        )
    )
)
