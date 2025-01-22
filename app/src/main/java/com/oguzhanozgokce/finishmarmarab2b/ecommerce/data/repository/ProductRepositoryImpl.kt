package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.createPager
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.toResourceMap
import com.oguzhanozgokce.finishmarmarab2b.core.data.network.safeApiCall
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.mapToProduct
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toQuestionAnswerDomain
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.mapper.product.toUserCommentDomain
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.paging.BasePagingSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.paging.GenericPagingSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.AddFavoriteProductRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.DeleteFavoriteProductRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.ToggleFavoriteRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ProductResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.ApiService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.UserComment
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ProductRepository {

    override fun getProducts(limit: Int, orderBy: String, sort: String): Flow<PagingData<Product>> {
        return createPager(
            pageSize = limit,
            pagingSourceFactory = {
                GenericPagingSource(
                    apiCall = { page ->
                        apiService.getProduct(page = page, limit = limit, orderBy = orderBy, sort = sort)
                    }
                )
            }
        ).map { pagingData ->
            pagingData.map { dto -> dto.mapToProduct() }
        }.combine(
            flow { emit(getFavoriteProductIds()) }
        ) { pagingData, favoriteProductIds ->
            pagingData.map { product ->
                product.copy(isFavorite = favoriteProductIds.contains(product.id))
            }
        }
    }

    override fun getFavoriteProducts(
        limit: Int,
        orderBy: String,
        sort: String
    ): Flow<PagingData<Product>> {
        return getPager(limit) { page, pageSize ->
            apiService.getFavoriteProducts(
                page = page,
                limit = pageSize,
                orderBy = orderBy,
                sort = sort
            )
        }.map { pagingData ->
            pagingData.map { dto -> dto.mapToProduct() }
        }
    }

    private suspend fun getFavoriteProductIds(): Set<Int> {
        val response =
            apiService.getFavoriteProducts(page = 1, limit = 1000, orderBy = "id", sort = "asc")
        return if (response.isSuccessful) {
            response.body()?.data?.list?.mapNotNull { it.id }?.toSet() ?: emptySet()
        } else {
            emptySet()
        }
    }

    private fun getPager(
        limit: Int,
        apiCall: suspend (page: Int, pageSize: Int) -> Response<ApiResponse<ProductResponse>>
    ): Flow<PagingData<ProductDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = limit,
                initialLoadSize = limit,
                prefetchDistance = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                BasePagingSource(apiCall, limit)
            }
        ).flow
    }

    override fun toggleFavorite(toggleFavoriteRequest: ToggleFavoriteRequest): Flow<Resource<Unit>> =
        flow {
            val response = safeApiCall { apiService.toggleFavorite(toggleFavoriteRequest) }
            emit(response)
        }

    override fun deleteFavoriteProduct(deleteFavoriteProductRequest: DeleteFavoriteProductRequest): Flow<Resource<Unit>> =
        flow {
            val response =
                safeApiCall { apiService.deleteFavoriteProduct(deleteFavoriteProductRequest) }
            emit(response)
        }

    override fun addProductToFavorites(addFavoriteProductRequest: AddFavoriteProductRequest): Flow<Resource<Unit>> =
        flow {
            val response =
                safeApiCall { apiService.addProductToFavorites(addFavoriteProductRequest) }
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

    override fun getProductQuestionsAndAnswers(productId: Int): Flow<PagingData<QuestionAnswer>> {
        return createPager(
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
    }

    override suspend fun getProductDetail(productId: Int): Resource<Product> {
        return safeApiCall {
            apiService.getProductDetail(productId)
        }.toResourceMap { productDto ->
            productDto.mapToProduct()
        }
    }
}
