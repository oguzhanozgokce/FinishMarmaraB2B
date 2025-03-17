package com.oguzhanozgokce.finishmarmarab2b

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository.AuthRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.LoginResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.RegisterResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.UserService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import junit.framework.TestCase.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
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
    private val userService: UserService = mock()
    private val localDataSource: LocalDataSource = mock()

    @Before
    fun setUp() {
        repository = AuthRepositoryImpl(userService, localDataSource)
    }

    @Test
    fun `signIn success - returns Unit`() = runTest {
        val signInRequest = SignInRequest(email = USER_EMAIL, password = USER_PASSWORD)
        val apiResponse = ApiResponse(
            status = true,
            message = SUCCESS,
            data = LoginResponse(id = USER_ID)
        )
        whenever(userService.signIn(signInRequest)).thenReturn(Response.success(apiResponse))
        val result = repository.signIn(signInRequest).first()
        assertTrue(result is Resource.Success)
        assertEquals(USER_ID, (result as Resource.Success).data.id)
        verify(localDataSource).saveOrUpdateUserId(USER_ID)
    }

    @Test
    fun `signIn error - returns error message`() = runTest {
        val signInRequest = SignInRequest(email = NOT_USER_EMAIL, password = USER_PASSWORD)
        val apiResponse = ApiResponse(
            status = false,
            message = ERROR,
            data = LoginResponse(id = USER_ID)
        )
        whenever(userService.signIn(signInRequest)).thenReturn(Response.success(apiResponse))
        val result = repository.signIn(signInRequest).first()
        assertTrue(result is Resource.Error)
        assertEquals(ERROR, (result as Resource.Error).message)
    }

    @Test
    fun `signIn exception - returns Resource Error`() = runTest {
        // Arrange
        val signInRequest = SignInRequest(email = USER_EMAIL, password = USER_PASSWORD)

        whenever(userService.signIn(signInRequest)).doAnswer {
            throw IOException(
                NO_INTERNET_CONNECTION
            )
        }

        val result = repository.signIn(signInRequest).first()

        assertTrue(result is Resource.Error)
        assertEquals("Network error: $NO_INTERNET_CONNECTION", (result as Resource.Error).message)
    }


    @Test
    fun `signUp success - saves user ID and returns success`() = runTest {
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

        whenever(userService.signUp(signUpRequest)).thenReturn(Response.success(apiResponse))

        val result = repository.signUp(signUpRequest).first()

        assertTrue(result is Resource.Success)
        assertEquals(USER_ID, (result as Resource.Success).data.id)

        assertEquals(USER_EMAIL, result.data.email)
        verify(localDataSource).saveOrUpdateUserId(USER_ID)
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
            data = RegisterResponse(id = -1, email = "")
        )

        whenever(userService.signUp(signUpRequest)).thenReturn(Response.success(apiResponse))

        val result = repository.signUp(signUpRequest).first()

        assertTrue(result is Resource.Error)

        val errorMessage = (result as Resource.Error).message
        assertEquals(ERROR, errorMessage)
    }

}
