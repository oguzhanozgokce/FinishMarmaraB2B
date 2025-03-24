package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis

import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.DELETE_FAVORITE_PRODUCT
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.DELETE_USER_ALL_SEARCH_HISTORY
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.DELETE_USER_SEARCH_HISTORY
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_CATEGORIES
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_COLLECTIONS
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_COMMENT_PRODUCT
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_FAVORITE_PRODUCTS
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_PRODUCTS
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_PRODUCT_DETAIL
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_PRODUCT_TOP5
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_QUESTIONS_PRODUCT
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_USER_SEARCH_HISTORY
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_COLLECTION
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_COLLECTION_ADD_PRODUCTS
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_TOGGLE_FAVORITE
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.CategoryDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.PaginationData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.QuestionAnswerDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.UserCommentDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostCollectionAddProductsRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostCollectionRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.ToggleFavoriteRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.DeleteFavoriteResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetCollectionsResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetFavoriteResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetSearchHistoryResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetSearchProductResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.PostToggleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET(GET_PRODUCTS)
    suspend fun getProduct(
        @Query("user_id") userId: Int,
        @Query("limit") limit: Int,
    ): Response<ApiResponse<PaginationData<ProductDto>>>

    @GET(GET_PRODUCTS)
    suspend fun getCategoryProducts(
        @Query("user_id") userId: Int,
        @Query("category_id") categoryId: Int,
    ): Response<ApiResponse<PaginationData<ProductDto>>>

    @GET(GET_FAVORITE_PRODUCTS)
    suspend fun getFavoriteProducts(
        @Path("user_id") userId: Int,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 4,
    ): Response<ApiResponse<PaginationData<GetFavoriteResponse>>>

    @POST(POST_TOGGLE_FAVORITE)
    suspend fun toggleFavorite(
        @Body request: ToggleFavoriteRequest,
    ): Response<ApiResponse<PostToggleResponse>>

    @DELETE(DELETE_FAVORITE_PRODUCT)
    suspend fun deleteFavoriteProduct(
        @Path("user_id") userId: Int,
        @Path("product_id") productId: Int,
    ): Response<ApiResponse<DeleteFavoriteResponse>>

    @GET(GET_COMMENT_PRODUCT)
    suspend fun getUserComments(
        @Path("product_id") productId: Int,
        @Query("page") page: Int,
        @Query("orderBy") orderBy: String,
        @Query("sort") sort: String,
    ): Response<ApiResponse<PaginationData<UserCommentDto>>>

    @GET(GET_PRODUCT_DETAIL)
    suspend fun getProductDetail(
        @Path("id") id: Int,
    ): Response<ApiResponse<ProductDto>>

    @GET(GET_QUESTIONS_PRODUCT)
    suspend fun getQuestionsAndAnswers(
        @Path("product_id") productId: Int,
        @Query("page") page: Int,
        @Query("orderBy") orderBy: String,
        @Query("sort") sort: String,
    ): Response<ApiResponse<PaginationData<QuestionAnswerDto>>>

    @GET(GET_CATEGORIES)
    suspend fun getCategories(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
    ): Response<ApiResponse<PaginationData<CategoryDto>>>

    @GET(GET_PRODUCT_TOP5)
    suspend fun getTop5Products(): Response<ApiResponse<List<ProductDto>>>

    @GET(GET_PRODUCTS)
    suspend fun getSearchProducts(
        @Query("user_id") userId: Int,
        @Query("searchTerm") searchQuery: String,
    ): Response<ApiResponse<GetSearchProductResponse>>

    @GET(GET_USER_SEARCH_HISTORY)
    suspend fun getUserSearchHistory(
        @Query("user_id") userId: Int,
    ): Response<ApiResponse<GetSearchHistoryResponse>>

    @DELETE(DELETE_USER_SEARCH_HISTORY)
    suspend fun deleteUserSearchHistory(
        @Path("id") id: Int,
    ): Response<ApiResponse<Unit>>

    @DELETE(DELETE_USER_ALL_SEARCH_HISTORY)
    suspend fun deleteUserAllSearchHistory(
        @Path("user_id") userId: Int,
    ): Response<ApiResponse<Unit>>

    @GET(GET_COLLECTIONS)
    suspend fun getCollections(
        @Path("user_id") userId: Int,
    ): Response<ApiResponse<GetCollectionsResponse>>

    @POST(POST_COLLECTION)
    suspend fun postCollection(
        @Body request: PostCollectionRequest
    ): Response<ApiResponse<Int>>

    @POST(POST_COLLECTION_ADD_PRODUCTS)
    suspend fun postCollectionAddProducts(
        @Body request: List<PostCollectionAddProductsRequest>
    ): Response<ApiResponse<Unit>>
}
