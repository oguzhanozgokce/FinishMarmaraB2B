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

            if (response.isSuccessful && response.body()?.status == true) {
                val body = response.body()?.data
                val items = body?.list ?: emptyList()
                val pagination = body?.pagination
                val currentPage = pagination?.page ?: STARTING_PAGE_INDEX
                val totalPages = pagination?.max ?: STARTING_PAGE_INDEX

                LoadResult.Page(
                    data = items,
                    prevKey = if (currentPage > STARTING_PAGE_INDEX) currentPage - 1 else null,
                    nextKey = if (currentPage < totalPages) currentPage + 1 else null
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        return page
    }
}

