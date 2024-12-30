package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.ApiService
import retrofit2.HttpException

class ProductPagingSource(
    private val apiService: ApiService,
    private val limit: Int,
    private val orderBy: String = "id",
    private val sort: String = "asc"
) : PagingSource<Int, ProductDto>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val INCREASE_PAGE_INDEX = 1
        private const val DECREASE_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductDto> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiService.getProduct(
                page = page,
                limit = limit,
                orderBy = orderBy,
                sort = sort
            )
            if (response.isSuccessful && response.body()?.status == true) {
                val body = response.body()?.data
                val products = body?.list ?: emptyList()
                val pagination = body?.pagination
                val currentPage = pagination?.page ?: page
                val totalPages = pagination?.max ?: STARTING_PAGE_INDEX
                return LoadResult.Page(
                    data = products,
                    prevKey = if (currentPage > 1) currentPage - DECREASE_PAGE_INDEX else null,
                    nextKey = if (currentPage < totalPages) currentPage + INCREASE_PAGE_INDEX else null
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
