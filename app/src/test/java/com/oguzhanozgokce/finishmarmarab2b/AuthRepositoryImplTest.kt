package com.oguzhanozgokce.finishmarmarab2b

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository.AuthRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.ApiService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.LoginResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.RegisterResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.whenever
import retrofit2.Response
import java.io.IOException

private const val USER_ID = 1
private const val USER_NAME = "Oguzhan"
private const val USER_SURNAME = "Ozgokce"
private const val USER_EMAIL = "ozgokceoguzhan34@fm.com"
private const val NOT_USER_EMAIL = "william.cushing@altostrat.com"
private const val USER_PASSWORD = "123456"
private const val USER_PHONE_NUMBER = "1234567890"
private const val SUCCESS = "Success"
private const val ERROR = "Error"
private const val NO_INTERNET_CONNECTION = "Network error: No internet connection."

class AuthRepositoryImplTest {
    private lateinit var repository: AuthRepositoryImpl
    private val apiService: ApiService = mock()
    private val localDataSource: LocalDataSource = mock()

    @Before
    fun setUp() {
        repository = AuthRepositoryImpl(apiService, localDataSource)
    }

    @Test
    fun `signIn success - returns Unit`() = runTest {
        val signInRequest = SignInRequest(
            email = USER_EMAIL,
            password = USER_PASSWORD
        )

        val apiResponse = ApiResponse(
            status = true,
            message = SUCCESS,
            data = LoginResponse(id = USER_ID)
        )
        val mockResponse = Response.success(apiResponse)
        whenever(apiService.signIn(signInRequest)).thenReturn(mockResponse)
        val result = repository.signIn(signInRequest).first()
        assert(result is Resource.Success)
        val response = (result as Resource.Success).data
        assertEquals(USER_ID, response.id)
        verify(localDataSource).saveOrUpdateUserId(USER_ID)
    }

    @Test
    fun `signIn error - returns error message`() = runTest {
        val signInRequest = SignInRequest(
            email = NOT_USER_EMAIL,
            password = USER_PASSWORD,
        )

        val apiResponse = ApiResponse(
            status = false,
            message = ERROR,
            data = LoginResponse(id = USER_ID)
        )

        val mockResponse = Response.success(apiResponse)

        whenever(apiService.signIn(signInRequest)).thenReturn(mockResponse)

        val result = repository.signIn(signInRequest).first()

        assert(result is Resource.Error)
        val errorMessage = (result as Resource.Error).message
        assertEquals(ERROR, errorMessage)
    }

    @Test
    fun `signIn exception - returns Resource Error with default message`() = runTest {
        val signInRequest = SignInRequest(
            email = USER_EMAIL,
            password = USER_PASSWORD,
        )
        whenever(apiService.signIn(signInRequest)).doAnswer {
            throw IOException(NO_INTERNET_CONNECTION)
        }
        val result = repository.signIn(signInRequest).first()
        assert(result is Resource.Error)
        val errorMessage = (result as Resource.Error).message
        assertEquals("Network error: $NO_INTERNET_CONNECTION", errorMessage)
    }

    @Test
    fun `signUp success - returns Unit`() = runTest {
        val signUpRequest = SignUpRequest(
            name = USER_NAME,
            surname = USER_SURNAME,
            email = USER_EMAIL,
            password = USER_PASSWORD,
            phoneNumber = USER_PHONE_NUMBER
        )

        val apiResponse = ApiResponse(
            status = true,
            message = SUCCESS,
            data = RegisterResponse(id = USER_ID, email = USER_EMAIL)
        )
        val mockResponse = Response.success(apiResponse)
        whenever(apiService.signUp(signUpRequest)).thenReturn(mockResponse)
        val result = repository.signUp(signUpRequest).first()
        assert(result is Resource.Success)
        val response = (result as Resource.Success).data
        assertEquals(USER_ID, response.id)
        assertEquals(USER_EMAIL, response.email)
    }


    @Test
    fun `signUp error - returns error message`() = runTest {
        val signUpRequest = SignUpRequest(
            name = USER_NAME,
            surname = USER_SURNAME,
            email = USER_EMAIL,
            password = USER_PASSWORD,
            phoneNumber = USER_PHONE_NUMBER
        )

        val apiResponse = ApiResponse(
            status = false,
            message = ERROR,
            data = RegisterResponse(id = USER_ID, email = USER_EMAIL)
        )

        val mockResponse = Response.success(apiResponse)

        whenever(apiService.signUp(signUpRequest)).thenReturn(mockResponse)

        val result = repository.signUp(signUpRequest).first()

        assert(result is Resource.Error)
        val errorMessage = (result as Resource.Error).message
        assertEquals(ERROR, errorMessage)
    }
}