package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ProductResponse
import retrofit2.HttpException
import retrofit2.Response

class BasePagingSource(
    private val apiCall: suspend (page: Int, limit: Int) -> Response<ApiResponse<ProductResponse>>,
    private val limit: Int
) : PagingSource<Int, ProductDto>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductDto> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiCall(page, limit)

            if (response.isSuccessful && response.body()?.status == true) {
                val body = response.body()?.data
                val items = body?.list ?: emptyList()
                val pagination = body?.pagination
                val currentPage = pagination?.page ?: page
                val totalPages = pagination?.max ?: STARTING_PAGE_INDEX

                LoadResult.Page(
                    data = items,
                    prevKey = if (currentPage > 1) currentPage - 1 else null,
                    nextKey = if (currentPage < totalPages) currentPage + 1 else null
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ProductDto>): Int? {
        return state.anchorPosition
    }
}

