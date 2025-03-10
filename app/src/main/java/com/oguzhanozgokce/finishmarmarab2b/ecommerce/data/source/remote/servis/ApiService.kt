package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis

import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.DELETE_BASKET
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.DELETE_BASKET_ALL
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.DELETE_FAVORITE_PRODUCT
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_BASKET
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_CATEGORIES
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_COMMENT_PRODUCT
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_FAVORITE_PRODUCTS
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_LOCATIONS
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_PRODUCTS
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_PRODUCT_DETAIL
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_PRODUCT_TOP5
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_QUESTIONS_PRODUCT
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_USER
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.LOGIN
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_ADD_FAVORITE_PRODUCT
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_BASKET
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_SAVE_ADDRESS
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_TOGGLE_FAVORITE
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.REGISTER
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.CategoryDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.PaginationData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.QuestionAnswerDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.UserCommentDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.AddFavoriteProductRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.PostProductBasketRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SaveLocationRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.ToggleFavoriteRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.BasketData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.DeleteFavoriteResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetFavoriteResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetLocationResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetSearchProductResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetUserResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.LoginResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.PostBasketResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.PostToggleResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST(LOGIN)
    suspend fun signIn(
        @Body request: SignInRequest,
    ): Response<ApiResponse<LoginResponse>>

    @POST(REGISTER)
    suspend fun signUp(
        @Body request: SignUpRequest,
    ): Response<ApiResponse<RegisterResponse>>

    @GET(GET_USER)
    suspend fun getUser(
        @Path("id") id: Int,
    ): Response<ApiResponse<GetUserResponse>>

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

    @POST(POST_ADD_FAVORITE_PRODUCT)
    suspend fun addProductToFavorites(
        @Body request: AddFavoriteProductRequest,
    ): Response<ApiResponse<Unit>>

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

    @POST(POST_BASKET)
    suspend fun addProductToBasket(
        @Body request: PostProductBasketRequest,
    ): Response<ApiResponse<PostBasketResponse>>

    @GET(GET_BASKET)
    suspend fun getBasket(
        @Path("user_id") userId: Int,
    ): Response<ApiResponse<BasketData>>

    @DELETE(DELETE_BASKET)
    suspend fun deleteBasket(
        @Path("user_id") userId: Int,
        @Path("product_id") productId: Int,
    ): Response<ApiResponse<Unit>>

    @DELETE(DELETE_BASKET_ALL)
    suspend fun deleteBasketAll(
        @Path("user_id") userId: Int,
    ): Response<ApiResponse<Unit>>

    @POST(POST_SAVE_ADDRESS)
    suspend fun saveAddress(
        @Body request: SaveLocationRequest,
    ): Response<ApiResponse<Unit>>

    @GET(GET_LOCATIONS)
    suspend fun getLocations(
        @Path("user_id") userId: Int,
    ): Response<ApiResponse<GetLocationResponse>>

    @GET(GET_PRODUCTS)
    suspend fun getSearchProducts(
        @Query("user_id") userId: Int,
        @Query("searchTerm") searchQuery: String,
    ): Response<ApiResponse<GetSearchProductResponse>>
}
