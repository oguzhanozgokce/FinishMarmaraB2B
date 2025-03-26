package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.createPager
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.mapDomain
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.orZero
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toResourceMap
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeApiCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.mapToPaginationData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.mapToProduct
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toCategoryDomain
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toCollectionListDomain
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toProduct
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toQuestionAnswerDomain
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toSearchHistoryListDomain
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toUserCommentDomain
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.PaginationData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.paging.GenericPagingSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostCollectionAddProductsRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostCollectionRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.ToggleFavoriteRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.DeleteCollectionResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.PostToggleResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.ProductService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Collection
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.SearchHistory
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.UserComment
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val localDataSource: LocalDataSource
) : ProductRepository {

    private suspend fun getUserId(): Int {
        return localDataSource.getUserId() ?: -1
    }

    override suspend fun getProducts(limit: Int): Resource<PaginationData<Product>> {
        val userId = getUserId()
        return safeApiCall {
            productService.getProduct(userId = userId, limit = limit)
        }.toResourceMap { paginationDataDto ->
            paginationDataDto.mapToPaginationData()
        }
    }

    override suspend fun getCategoryProducts(categoryId: Int): Resource<PaginationData<Product>> {
        val userId = getUserId()
        return safeApiCall {
            productService.getCategoryProducts(userId = userId, categoryId = categoryId)
        }.toResourceMap { paginationDataDto ->
            paginationDataDto.mapToPaginationData()
        }
    }

    override suspend fun getFavoriteProducts(): Flow<PagingData<Product>> {
        val userId = getUserId()
        return createPager(
            pagingSourceFactory = {
                GenericPagingSource(
                    apiCall = { page ->
                        productService.getFavoriteProducts(userId = userId, page = page)
                    }
                )
            }
        ).map { pagingData ->
            pagingData.map {
                it.toProduct()
            }
        }
    }

    override suspend fun toggleFavorite(productId: Int): Resource<PostToggleResponse> {
        val request = ToggleFavoriteRequest(
            userId = getUserId(),
            productId = productId,
        )
        return safeApiCall { productService.toggleFavorite(request) }
    }

    override suspend fun deleteFavoriteProduct(productId: Int): Resource<Int> {
        val userId = getUserId()
        return safeApiCall {
            productService.deleteFavoriteProduct(userId, productId)
        }.toResourceMap { response ->
            response.productId
        }
    }

    override fun getProductComments(productId: Int): Flow<PagingData<UserComment>> {
        return createPager(
            pagingSourceFactory = {
                GenericPagingSource(
                    apiCall = { page ->
                        productService.getUserComments(
                            productId = productId,
                            page = page,
                            orderBy = "id",
                            sort = "asc"
                        )
                    }
                )
            }
        ).mapDomain { it.toUserCommentDomain() }
    }

    override fun getProductQuestionsAndAnswers(productId: Int): Flow<PagingData<QuestionAnswer>> =
        createPager(
            pagingSourceFactory = {
                GenericPagingSource(
                    apiCall = { page ->
                        productService.getQuestionsAndAnswers(
                            productId = productId,
                            page = page,
                            orderBy = "id",
                            sort = "asc"
                        )
                    }
                )
            }
        ).map { pagingData ->
            pagingData.map { it.toQuestionAnswerDomain() }
        }

    override suspend fun getProductDetail(productId: Int): Resource<Product> {
        return safeApiCall {
            productService.getProductDetail(productId)
        }.toResourceMap { productDto ->
            productDto.mapToProduct()
        }
    }

    override fun getCategories(): Flow<PagingData<Category>> = createPager(
        pagingSourceFactory = {
            GenericPagingSource(
                apiCall = { page ->
                    productService.getCategories(page = page)
                }
            )
        }
    ).map { pagingData ->
        pagingData.map { it.toCategoryDomain() }
    }

    override suspend fun getTop5Products(): Resource<List<Product>> {
        return safeApiCall { productService.getTop5Products() }.toResourceMap { productDtoList ->
            productDtoList.map { it.mapToProduct() }
        }
    }

    override suspend fun getSearchHistory(): Resource<List<SearchHistory>> {
        val userId = getUserId()
        return safeApiCall { productService.getUserSearchHistory(userId) }
            .toResourceMap { response ->
                response.toSearchHistoryListDomain()
            }
    }

    override suspend fun deleteSearchHistory(id: Int): Resource<Unit> {
        return safeApiCall { productService.deleteUserSearchHistory(id) }
    }

    override suspend fun deleteAllSearchHistory(): Resource<Unit> {
        val userId = getUserId()
        return safeApiCall { productService.deleteUserAllSearchHistory(userId) }
    }

    override suspend fun getCollection(): Resource<List<Collection>> {
        val userId = getUserId()
        return safeApiCall { productService.getCollections(userId) }
            .toResourceMap { response ->
                response.toCollectionListDomain()
            }
    }

    override suspend fun postCollection(name: String): Resource<Int> {
        val collectionRequest = PostCollectionRequest(
            name = name,
            userId = getUserId()
        )
        return safeApiCall { productService.postCollection(collectionRequest) }
            .toResourceMap { it.id.orZero() }
    }

    override suspend fun postCollectionAddProducts(request: List<PostCollectionAddProductsRequest>): Resource<Unit> {
        return safeApiCall { productService.postCollectionAddProducts(request) }
    }

    override suspend fun deleteCollection(collectionId: Int): Resource<DeleteCollectionResponse> {
        return safeApiCall { productService.deleteCollection(collectionId) }
            .toResourceMap { it }
    }

    override suspend fun putCollection(collectionId: Int, newName: String): Resource<Unit> {
        return safeApiCall { productService.putCollection(collectionId, newName) }
    }
}