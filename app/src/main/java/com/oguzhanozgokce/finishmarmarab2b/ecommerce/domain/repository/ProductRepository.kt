package com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository

import androidx.paging.PagingData
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.PaginationData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostCollectionAddProductsRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.DeleteCollectionResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.PostToggleResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Collection
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
    suspend fun getSearchHistory(): Resource<List<SearchHistory>>
    suspend fun deleteSearchHistory(id: Int): Resource<Unit>
    suspend fun deleteAllSearchHistory(): Resource<Unit>
    suspend fun getCollection(): Resource<List<Collection>>
    suspend fun postCollection(name: String): Resource<Int>
    suspend fun postCollectionAddProducts(request: List<PostCollectionAddProductsRequest>): Resource<Unit>
    suspend fun deleteCollection(collectionId: Int): Resource<DeleteCollectionResponse>
}
