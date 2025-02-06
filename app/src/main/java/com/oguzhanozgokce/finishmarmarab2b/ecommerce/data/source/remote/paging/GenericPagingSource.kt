package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.PaginationData
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import retrofit2.HttpException
import retrofit2.Response

class GenericPagingSource<T : Any>(
    private val apiCall: suspend (page: Int) -> Response<ApiResponse<PaginationData<T>>>
) : PagingSource<Int, T>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiCall(page)

            if (response.isSuccessful) {
                response.body()?.let { apiResponse ->
                    if (apiResponse.status) {
                        val paginationData = apiResponse.data
                        val items = paginationData.list ?: emptyList()
                        val pagination = paginationData.pagination
                        val currentPage = pagination?.page ?: STARTING_PAGE_INDEX
                        val totalPages = pagination?.max ?: STARTING_PAGE_INDEX

                        return LoadResult.Page(
                            data = items,
                            prevKey = if (currentPage > STARTING_PAGE_INDEX) currentPage - 1 else null,
                            nextKey = if (currentPage < totalPages) currentPage + 1 else null
                        )
                    } else {
                        return LoadResult.Error(Exception(apiResponse.message))
                    }
                } ?: LoadResult.Error(Exception("Response body is null"))
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let { closestPage ->
                closestPage.prevKey?.plus(1) ?: closestPage.nextKey?.minus(1)
            }
        }
    }
}
