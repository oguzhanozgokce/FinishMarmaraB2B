package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.createPager
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.mapToPaginationData
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toResourceMap
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeApiCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.mapToProduct
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toCategoryDomain
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toProduct
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toQuestionAnswerDomain
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toUserCommentDomain
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.PaginationData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.paging.GenericPagingSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.AddFavoriteProductRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.DeleteFavoriteProductRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.ToggleFavoriteRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.PostToggleResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.ApiService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.UserComment
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) : ProductRepository {

    private suspend fun getUserId(): Int {
        return localDataSource.getUserId() ?: -1
    }

    override suspend fun getProducts(limit: Int): Resource<PaginationData<Product>> {
        val userId = getUserId()
        return safeApiCall {
            apiService.getProduct(userId = userId, limit = limit)
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
                        apiService.getFavoriteProducts(userId = userId, page = page)
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
        return safeApiCall { apiService.toggleFavorite(request) }
    }

    override suspend fun deleteFavoriteProduct(productId: Int): Resource<Int> {
        val userId = getUserId()
        val request = DeleteFavoriteProductRequest(
            userId = userId,
            productId = productId
        )
        return safeApiCall {
            apiService.deleteFavoriteProduct(request)
        }.toResourceMap { response ->
            response.productId
        }
    }

    override fun addProductToFavorites(request: AddFavoriteProductRequest): Flow<Resource<Unit>> =
        flow {
            val response =
                safeApiCall { apiService.addProductToFavorites(request) }
            emit(response)
        }

    override fun getProductComments(productId: Int): Flow<PagingData<UserComment>> {
        return createPager(
            pagingSourceFactory = {
                GenericPagingSource(
                    apiCall = { page ->
                        apiService.getUserComments(
                            productId = productId,
                            page = page,
                            orderBy = "id",
                            sort = "asc"
                        )
                    }
                )
            }
        ).map { pagingData ->
            pagingData.map { it.toUserCommentDomain() }
        }
    }

    override fun getProductQuestionsAndAnswers(productId: Int): Flow<PagingData<QuestionAnswer>> =
        createPager(
            pagingSourceFactory = {
                GenericPagingSource(
                    apiCall = { page ->
                        apiService.getQuestionsAndAnswers(
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
            apiService.getProductDetail(productId)
        }.toResourceMap { productDto ->
            productDto.mapToProduct()
        }
    }

    override fun getCategories(): Flow<PagingData<Category>> = createPager(
        pagingSourceFactory = {
            GenericPagingSource(
                apiCall = { page ->
                    apiService.getCategories(page = page)
                }
            )
        }
    ).map { pagingData ->
        pagingData.map { it.toCategoryDomain() }
    }
}
