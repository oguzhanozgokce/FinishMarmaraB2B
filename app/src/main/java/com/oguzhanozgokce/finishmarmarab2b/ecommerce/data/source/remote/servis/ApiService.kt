package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis

import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_COMMENT_PRODUCT
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_FAVORITE_PRODUCTS
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_PRODUCTS
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_PRODUCT_DETAIL
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_QUESTIONS_PRODUCT
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.GET_USER
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.LOGIN
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_ADD_FAVORITE_PRODUCT
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_DELETE_FAVORITE_PRODUCT
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.POST_TOGGLE_FAVORITE
import com.oguzhanozgokce.finishmarmarab2b.core.common.ApiRoutes.REGISTER
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.PaginationData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.QuestionAnswerDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.UserCommentDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.AddFavoriteProductRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.DeleteFavoriteProductRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.ToggleFavoriteRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetUserResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.LoginResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ProductResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST(LOGIN)
    suspend fun signIn(
        @Body request: SignInRequest
    ): Response<ApiResponse<LoginResponse>>

    @POST(REGISTER)
    suspend fun signUp(
        @Body request: SignUpRequest
    ): Response<ApiResponse<RegisterResponse>>

    @GET(GET_USER)
    suspend fun getUser(
        @Path("id") id: Int
    ): Response<ApiResponse<GetUserResponse>>

    @GET(GET_PRODUCTS)
    suspend fun getProduct(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") orderBy: String,
        @Query("sort") sort: String
    ): Response<ApiResponse<PaginationData<ProductDto>>>

    @GET(GET_FAVORITE_PRODUCTS)
    suspend fun getFavoriteProducts(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") orderBy: String,
        @Query("sort") sort: String
    ) : Response<ApiResponse<ProductResponse>>

    @POST(POST_TOGGLE_FAVORITE)
    suspend fun toggleFavorite(
        @Body request: ToggleFavoriteRequest
    ) : Response<ApiResponse<Unit>>

    @POST(POST_DELETE_FAVORITE_PRODUCT)
    suspend fun deleteFavoriteProduct(
        @Body request: DeleteFavoriteProductRequest
    ) : Response<ApiResponse<Unit>>

    @POST(POST_ADD_FAVORITE_PRODUCT)
    suspend fun addProductToFavorites(
        @Body request: AddFavoriteProductRequest
    ) : Response<ApiResponse<Unit>>

    @GET(GET_COMMENT_PRODUCT)
    suspend fun getUserComments(
        @Path("product_id") productId: Int,
        @Query("page") page: Int,
        @Query("orderBy") orderBy: String,
        @Query("sort") sort: String
    ) :  Response<ApiResponse<PaginationData<UserCommentDto>>>

    @GET(GET_PRODUCT_DETAIL)
    suspend fun getProductDetail(
        @Path("id") id: Int
    ) : Response<ApiResponse<ProductDto>>

    @GET(GET_QUESTIONS_PRODUCT)
    suspend fun getQuestionsAndAnswers(
        @Path("product_id") productId: Int,
        @Query("page") page: Int,
        @Query("orderBy") orderBy: String,
        @Query("sort") sort: String
    ) : Response<ApiResponse<PaginationData<QuestionAnswerDto>>>

}

