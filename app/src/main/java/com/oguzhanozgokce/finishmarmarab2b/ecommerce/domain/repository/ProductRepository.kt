package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.PaginationData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.PostToggleResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.SearchHistory
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.UserComment
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(limit: Int): Resource<PaginationData<Product>>
    suspend fun getCategoryProducts(categoryId: Int): Resource<PaginationData<Product>>
    suspend fun getFavoriteProducts(): Flow<PagingData<Product>>
    suspend fun toggleFavorite(productId: Int): Resource<PostToggleResponse>
    suspend fun deleteFavoriteProduct(productId: Int): Resource<Int>
    fun getProductComments(productId: Int): Flow<PagingData<UserComment>>
    fun getProductQuestionsAndAnswers(productId: Int): Flow<PagingData<QuestionAnswer>>
    suspend fun getProductDetail(productId: Int): Resource<Product>
    fun getCategories(): Flow<PagingData<Category>>
    suspend fun getTop5Products(): Resource<List<Product>>
    suspend fun postProductBasket(productId: Int): Resource<Int>
    suspend fun getBasketProducts(): Resource<List<Product>>
    suspend fun deleteBasketProduct(productId: Int): Resource<Unit>
    suspend fun deleteBasketAll(): Resource<Unit>
    suspend fun getSearchHistory(): Resource<List<SearchHistory>>
    suspend fun deleteSearchHistory(id: Int): Resource<Unit>
}
