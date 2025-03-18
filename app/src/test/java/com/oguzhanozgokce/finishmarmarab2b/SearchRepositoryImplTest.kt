package com.oguzhanozgokce.finishmarmarab2b

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository.SearchRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.ProductDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetSearchProductResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.ProductService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

private const val USER_ID = 1

@ExperimentalCoroutinesApi
class SearchRepositoryImplTest {

    private lateinit var repository: SearchRepositoryImpl
    private val productService: ProductService = mock()
    private val localDataSource: LocalDataSource = mock()

    @Before
    fun setUp() {
        repository = SearchRepositoryImpl(productService, localDataSource)
    }

    @Test
    fun `getSearchProducts success - returns product list`() = runTest {
        val searchQuery = "Test Product 2"
        val userId = USER_ID
        val mockProducts = listOf(
            ProductDto(id = 1, title = "Laptop 1", price = 1000.0),
            ProductDto(id = 2, title = "Laptop 2", price = 1500.0)
        )
        val apiResponse = ApiResponse(
            status = true,
            message = "Success",
            data = GetSearchProductResponse(list = mockProducts)
        )

        whenever(localDataSource.getUserId()).thenReturn(userId)
        whenever(productService.getSearchProducts(userId, searchQuery))
            .thenReturn(Response.success(apiResponse))

        val result = repository.getSearchProducts(searchQuery)
        assertTrue(result is Resource.Success)
    }
}
