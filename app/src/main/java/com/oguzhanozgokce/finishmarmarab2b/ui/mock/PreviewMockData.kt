package com.oguzhanozgokce.finishmarmarab2b.ui.mock

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Image
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Seller
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.UserComment
import com.oguzhanozgokce.finishmarmarab2b.ui.search.HistorySearch
import com.oguzhanozgokce.finishmarmarab2b.ui.search.PopularSearch
import java.time.LocalDateTime

object PreviewMockData {

    val defaultProduct = Product(
        id = 1,
        title = "Test Product",
        description = "A nice test product description",
        price = 100.0,
        discountedPrice = 80.0,
        sellerId = 123,
        stock = 10,
        rate = 4.5,
        commentCount = 15,
        categoryId = 1,
        createdAt = LocalDateTime.now(),
        category = Category(
            id = 1,
            name = "Tech"
        ),
        seller = Seller(
            id = 123,
            name = "Test Seller",
        ),
        images = listOf(
            Image(
                "https://img.freepik.com/free-vector/realistic-natural-cream-ad-template_52683-19298.jpg?t=st=1737368792~exp=1737372392~hmac=23a9141fa69e0f8ec951357a3df6b37447768a76207c649cd152be0ba2c74f62&w=1800"
            ),
            Image(
                "https://img.freepik.com/free-vector/cosmetic-realistic-with-cream-jar_88138-122.jpg?t=st=1737368852~exp=1737372452~hmac=0036a43a7ec668f2189018a44f07a57a6ee1beec303046890a0f5d21161e5519&w=2000"
            )
        ),
        isFavorite = false
    )

    val outOfStockProduct = defaultProduct.copy(
        stock = 0,
        isFavorite = true,
        title = "Out of Stock Product"
    )

    val productWithNoImage = defaultProduct.copy(
        images = emptyList(),
        title = "No Image Product"
    )

    val defaultQuestionAnswer = QuestionAnswer(
        id = 1,
        question = "Bu ürün hangi cihazlarla uyumludur? Bu ürün hangi cihazlarla uyumludur? Bu ürün hangi cihazlarla uyumludur?",
        answer = "Bluetooth destekleyen tüm cihazlarla uyumludur. Bluetooth destekleyen tüm cihazlarla uyumludur. ",
        date = "20 Ocak 2025 | 12:00",
        userName = "O** O**",
        sellerName = "Nimbus Market",
        sellerImageUrl = "https://upload.wikimedia.org/wikipedia/tr/7/70/Marmara_%C3%9Cniversitesi_logo.png"
    )

    val sampleQuestionAnswersData = listOf(
        QuestionAnswer(
            id = 1,
            question = "Bu ürün hangi cihazlarla uyumludur? aaaaaaasdasdasdasdsadasdasdasdasd",
            answer = "Bluetooth destekleyen tüm cihazlarla uyumludur.",
            date = "20 Ocak 2025 | 12:00",
            userName = "O** O**",
            sellerName = "Nimbus Market",
            sellerImageUrl = "https://upload.wikimedia.org/wikipedia/tr/7/70/Marmara_%C3%9Cniversitesi_logo.png"
        ),
        QuestionAnswer(
            id = 2,
            question = "Pil ömrü ne kadar?",
            answer = "Ortalama kullanımda 10 saat pil ömrü sunar.",
            date = "21 Ocak 2025 | 15:00",
            userName = "A** A**",
            sellerName = "Nimbus Market",
            sellerImageUrl = "https://upload.wikimedia.org/wikipedia/tr/7/70/Marmara_%C3%9Cniversitesi_logo.png"
        ),
        QuestionAnswer(
            id = 3,
            question = "Hangi renkler vardır?",
            answer = "Siyah, Beyaz, Kırmızı renkler vardır.",
            date = "22 Ocak 2025 | 17:58",
            userName = "B** B**",
            sellerName = "Nimbus Market",
            sellerImageUrl = "https://upload.wikimedia.org/wikipedia/tr/7/70/Marmara_%C3%9Cniversitesi_logo.png"
        )
    )

    val defaultUserComment = UserComment(
        id = 1,
        rating = 4.5,
        date = "20 Ocak 2025 | 12:00",
        userName = "O** O**",
        sellerName = "Nimbus Market",
        comment = "Bu ürün çok iyi bir ürün. Bu ürün çok iyi bir ürün. Bu ürün çok iyi bir ürün. Bu ürün çok iyi bir ürün. Bu ürün çok iyi bir ürün. Bu ürün çok iyi bir ürün. Bu ürün çok iyi bir ürün. Bu ürün çok iyi bir ürün. Bu ürün çok iyi bir ürün.",
    )

    val historyList = listOf(
        HistorySearch(text = "Iphone 13"),
        HistorySearch(text = "Headphones"),
        HistorySearch(text = "Camera"),
        HistorySearch(text = "Iphone 13"),
        HistorySearch(text = "Headphones"),
        HistorySearch(text = "Camera"),
    )

    val popularList = listOf(
        PopularSearch(text = "Iphone 13"),
        PopularSearch(text = "Headphones"),
        PopularSearch(text = "Camera"),
        PopularSearch(text = "Iphone 13"),
        PopularSearch(text = "Headphones"),
        PopularSearch(text = "Camera"),
    )
}
