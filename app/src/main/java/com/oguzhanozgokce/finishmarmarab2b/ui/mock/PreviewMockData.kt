package com.oguzhanozgokce.finishmarmarab2b.ui.mock

import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CardType
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Collection
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Image
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Location
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.OrderInfo
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Province
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.SearchHistory
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Seller
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.UserComment
import java.time.LocalDateTime

object PreviewMockData {

    val defaultProduct = Product(
        id = 1,
        title = "Test Product",
        description = "A nice test product description",
        price = 100.0,
        discountedPrice = 80.0,
        percentageRate = "%20",
        sellerId = 123,
        stock = 10,
        rate = 4.5,
        commentCount = 15,
        favoriteCount = 5,
        basketCount = 2,
        categoryId = 1,
        createdAt = LocalDateTime.now(),
        category = Category(
            id = 1,
            name = "Tech",
            categoryImage = ""
        ),
        seller = Seller(
            id = 123,
            name = "Test Seller",
        ),
        images = listOf(
            Image(
                id = 2,
                productId = 1,
                imageUrl = "https://img.freepik.com/free-vector/realistic-natural-cream-ad-template_52683-19298.jpg?t=st=1737368792~exp=1737372392~hmac=23a9141fa69e0f8ec951357a3df6b37447768a76207c649cd152be0ba2c74f62&w=1800"
            ),
            Image(
                id = 3,
                productId = 1,
                imageUrl = "https://img.freepik.com/free-vector/cosmetic-realistic-with-cream-jar_88138-122.jpg?t=st=1737368852~exp=1737372452~hmac=0036a43a7ec668f2189018a44f07a57a6ee1beec303046890a0f5d21161e5519&w=2000"
            )
        ),
        isFavorite = false
    )

    val defaultProductList = listOf(
        defaultProduct,
        defaultProduct.copy(id = 2, title = "Test Product 2"),
        defaultProduct.copy(id = 3, title = "Test Product 3"),
        defaultProduct.copy(id = 4, title = "Test Product 4"),
        defaultProduct.copy(id = 5, title = "Test Product 5"),
        defaultProduct.copy(id = 6, title = "Test Product 6"),
    )

    private val defaultCategory = Category(
        id = 1,
        name = "Tech",
        categoryImage = ""
    )

    val defaultCategoryList = listOf(
        defaultCategory,
        defaultCategory.copy(id = 2, name = "Test Category 2"),
        defaultCategory.copy(id = 3, name = "Test Category 3"),
        defaultCategory.copy(id = 4, name = "Test Category 4"),
        defaultCategory.copy(id = 5, name = "Test Category 5"),
        defaultCategory.copy(id = 6, name = "Test Category 6"),
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
        ),
        QuestionAnswer(
            id = 4,
            question = "Bu ürünün garanti süresi ne kadar ve garanti kapsamında hangi durumlar yer alıyor? Ayrıca, garanti süresi boyunca kullanıcı hatası sayılabilecek durumlar neler olur?",
            answer = "Bu ürün, üretici firma tarafından **2 yıl resmi garanti** kapsamında sunulmaktadır. Garanti süresi boyunca, üretici hatalarından kaynaklanan arızalar ve bileşen sorunları ücretsiz olarak giderilir. Ancak, kullanıcı hatasına bağlı olan ekran kırılmaları, sıvı teması, aşırı sıcaklığa maruz bırakma ve yanlış kullanım nedeniyle oluşabilecek mekanik arızalar garanti kapsamına girmez. Eğer ürünle ilgili herhangi bir garanti talebiniz olursa, yetkili servis noktalarına başvurabilirsiniz veya müşteri hizmetlerimizle iletişime geçebilirsiniz.",
            date = "23 Ocak 2025 | 09:45",
            userName = "C** C**",
            sellerName = "Nimbus Market",
            sellerImageUrl = "https://upload.wikimedia.org/wikipedia/tr/7/70/Marmara_%C3%9Cniversitesi_logo.png"
        ),
        QuestionAnswer(
            id = 5,
            question = "Bu telefonun kamera özellikleri nelerdir? Gece çekim modu var mı ve düşük ışıkta nasıl bir performans sunar? Ayrıca, video kayıt yetenekleri hakkında bilgi verebilir misiniz?",
            answer = "Bu model, **50 MP ana kamera, 12 MP ultra geniş açılı lens ve 10 MP telefoto lens** olmak üzere üçlü kamera sistemine sahiptir. **Gece modu** sayesinde düşük ışık koşullarında bile net ve parlak fotoğraflar çekebilirsiniz. Yapay zeka destekli görüntü işleme teknolojisi sayesinde gürültü azaltma ve kontrast optimizasyonu sağlanarak gece çekimlerinde daha canlı renkler elde edilir. Video kayıt özelliklerine gelince, **4K 60 FPS** ve **1080p 240 FPS ağır çekim** destekleri bulunmaktadır. Aynı zamanda **OIS (Optik Görüntü Sabitleme)** özelliği sayesinde hareketli sahnelerde bile net görüntüler elde edebilirsiniz.",
            date = "24 Ocak 2025 | 12:30",
            userName = "D** D**",
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
        SearchHistory(
            id = 2,
            userId = 4,
            searchHistory = "Iphone 13",
            createdAt = "20 Ocak 2025 | 12:00"
        ),
        SearchHistory(
            id = 2,
            userId = 4,
            searchHistory = "Headphones",
            createdAt = "20 Ocak 2025 | 12:00"
        ),
        SearchHistory(
            id = 2,
            userId = 4,
            searchHistory = "Camera",
            createdAt = "20 Ocak 2025 | 12:00"
        ),
        SearchHistory(
            id = 2,
            userId = 4,
            searchHistory = "Headphones",
            createdAt = "20 Ocak 2025 | 12:00"
        )
    )

    val defaultSearchHistory = SearchHistory(
        id = 2,
        userId = 2,
        searchHistory = "Iphone 13",
        createdAt = "20 Ocak 2025 | 12:00"
    )

    val defaultLongSearchHistory = SearchHistory(
        id = 3,
        searchHistory = "HeadphonesHeadphones",
        userId = 2,
        createdAt = "20 Ocak 2025 | 12:00"
    )

    val sampleCities = listOf(
        Province("Ankara", listOf("Çankaya", "Keçiören", "Sincan")),
        Province("İstanbul", listOf("Kadıköy", "Beşiktaş", "Şişli")),
        Province("İzmir", listOf("Konak", "Karşıyaka", "Bornova")),
        Province("Adana", listOf("Seyhan", "Gelendost", "Keşan")),
        Province("Antalya", listOf("Yenimahalle", "Keçiören", "Çankaya")),
        Province("Bursa", listOf("Osmangazi", "Nilüfer", "Osmangazi")),
    )

    val defaultCart = listOf(
        CreditCart(
            id = 2,
            userId = 2,
            cardTitle = "Test",
            cardNumber = "5367890123456436",
            cardNameSurname = "John Doe",
            lastDate = "12/25",
            cardCvv = "123",
            cardType = CardType.MASTERCARD
        ),
        CreditCart(
            id = 3,
            userId = 2,
            cardTitle = "Test",
            cardNumber = "4367890123456436",
            cardNameSurname = "Jane Smith",
            lastDate = "11/26",
            cardCvv = "456",
            cardType = CardType.VISA
        ),
    )

    val defaultMasterCart = CreditCart(
        id = 2,
        userId = 2,
        cardTitle = "Test",
        cardNumber = "5367890123456436",
        cardNameSurname = "John Doe",
        lastDate = "12/25",
        cardCvv = "123",
        cardType = CardType.MASTERCARD
    )

    val defaultVisaCart = CreditCart(
        id = 2,
        userId = 2,
        cardTitle = "Test",
        cardNumber = "5367890123456436",
        lastDate = "12/25",
        cardNameSurname = "John Doe",
        cardCvv = "123",
        cardType = CardType.VISA
    )

    val defaultLocationLists = listOf(
        Location(
            id = 1,
            userId = 101,
            province = "İstanbul",
            city = "Kadıköy",
            openAddress = "Bağdat Caddesi No:123 Daire:5",
            addressTel = "0532 123 45 67",
            addressTitle = "Ev",
            nameSurname = "Ali Yılmaz"
        ),
        Location(
            id = 2,
            userId = 102,
            province = "Ankara",
            city = "Çankaya",
            openAddress = "Atatürk Bulvarı No:456 Kat:3",
            addressTel = "0543 987 65 43",
            addressTitle = "İş",
            nameSurname = "Ayşe Demir"
        ),
        Location(
            id = 3,
            userId = 103,
            province = "İzmir",
            city = "Konak",
            openAddress = "Cumhuriyet Meydanı No:78",
            addressTel = "0505 654 32 10",
            addressTitle = "Aile Evi",
            nameSurname = "Mehmet Can"
        ),
        Location(
            id = 4,
            userId = 104,
            province = "Bursa",
            city = "Osmangazi",
            openAddress = "Heykel Mahallesi, Çarşı Sokak No:12",
            addressTel = "0551 321 98 76",
            addressTitle = "Diğer",
            nameSurname = "Zeynep Korkmaz"
        )
    )

    val defaultLocation = Location(
        id = 1,
        userId = 101,
        province = "İstanbul",
        city = "Kadıköy",
        openAddress = "Bağdat Caddesi No:123 Daire:5",
        addressTel = "0532 123 45 67",
        addressTitle = "Ev",
        nameSurname = "Ali Yılmaz"
    )

    val defaultCollection = Collection(
        id = 1,
        name = "Tecnology",
        productCount = 12,
        imageList = listOf()
    )

    val defaultCollectionList = listOf(
        Collection(
            id = 1,
            name = "Tecnology",
            productCount = 12,
            imageList = listOf(
                "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg",
                "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg",
                "https://cdn.dsmcdn.com/ty1509/product/media/images/prod/QC/20240828/15/bdd9d479-d4ba-3bbd-ba55-a34d282fdbcf/1_org_zoom.jpg",
            )
        ),
        Collection(
            id = 2,
            name = "Health",
            productCount = 12,
            imageList = listOf(
                "https://cdn.dsmcdn.com/ty1630/prod/QC/20250131/23/223e2ea9-93b6-3dea-9f5d-6ba177c223c5/1_org_zoom.jpg",
                "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg",
                "https://cdn.dsmcdn.com/ty1509/product/media/images/prod/QC/20240828/15/bdd9d479-d4ba-3bbd-ba55-a34d282fdbcf/1_org_zoom.jpg",
            )
        ),
        Collection(
            id = 3,
            name = "Clothing",
            productCount = 12,
            imageList = listOf(
                "https://cdn.dsmcdn.com/mnresize/200/303/ty1519/product/media/images/prod/QC/20240902/08/9b364bb3-da69-3a4b-8278-55de1959c147/1_org_thumb.jpg",
                "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg",
                "https://cdn.dsmcdn.com/ty1509/product/media/images/prod/QC/20240828/15/bdd9d479-d4ba-3bbd-ba55-a34d282fdbcf/1_org_zoom.jpg",
            )
        )
    )

    val defaultOrderInfo = OrderInfo(
        id = 1,
        userId = 2,
        totalPrice = 199.99,
        orderStatus = "Pending",
        createdAt = "2025-03-18",
        updatedAt = "2025-03-18",
        orderImage = listOf(
            "https://cdn.vatanbilgisayar.com/Upload/PRODUCT/samsung/thumb/1-171_large.jpg",
            "https://cdn.akakce.com/z/marshall/marshall-major-iv-katlanabilir-kablosuz-kulak-ustu.jpg"
        )
    )

    val defaultOrderInfoList = listOf(
        OrderInfo(
            id = 1,
            userId = 2,
            totalPrice = 199.99,
            orderStatus = "Pending",
            createdAt = "2025-03-18",
            updatedAt = "2025-03-18",
            orderImage = listOf(
                "https://cdn.vatanbilgisayar.com/Upload/PRODUCT/samsung/thumb/1-171_large.jpg",
                "https://cdn.akakce.com/z/marshall/marshall-major-iv-katlanabilir-kablosuz-kulak-ustu.jpg"
            )
        ),
        OrderInfo(
            id = 2,
            userId = 3,
            totalPrice = 349.50,
            orderStatus = "Shipped",
            createdAt = "2025-03-15",
            updatedAt = "2025-03-15",
            orderImage = listOf(
                "https://cdn.akakce.com/z/samsung/samsung-990-evo-plus-mz-v9s1t0bw-pci-express-1-tb-m-2.jpg",
                "https://iis-akakce.akamaized.net/p.x?%2f%2fproductimages.hepsiburada.net%2fs%2f777%2f600%2f110000805819136.jpg"
            )
        ),
        OrderInfo(
            id = 3,
            userId = 4,
            totalPrice = 89.99,
            orderStatus = "Delivered",
            createdAt = "2025-03-12",
            updatedAt = "2025-03-12",
            orderImage = listOf(
                "https://cdn.akakce.com/z/apple/apple-watch-series-10-gps-46mm-aluminyum-kasa-spor-kordon.jpg",
                "https://cdn.akakce.com/x/samsung/samsung-galaxy-watch-7-44mm.jpg"
            )
        )
    )
}
