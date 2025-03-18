package com.oguzhanozgokce.finishmarmarab2b

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository.AuthRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.GetUserResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.LoginResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.RegisterResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.servis.UserService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.User
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

    @Test
    fun `getUser success - returns mapped user data`() = runTest {
        val userId = USER_ID
        val apiResponse = ApiResponse(
            status = true,
            message = SUCCESS,
            data = GetUserResponse(userId, USER_NAME, USER_SURNAME, USER_EMAIL)
        )

        val expectedUser = User(userId, USER_NAME, USER_SURNAME, USER_EMAIL)

        whenever(localDataSource.getUserId()).thenReturn(userId)
        whenever(userService.getUser(userId)).thenReturn(Response.success(apiResponse))

        val result = repository.getUser().first()

        assertTrue(result is Resource.Success)
        assertEquals(expectedUser, (result as Resource.Success).data)
    }

    @Test
    fun `getUser fails when userId is null`() = runTest {
        whenever(localDataSource.getUserId()).thenReturn(null)

        val result = repository.getUser().first()

        assertTrue(result is Resource.Error)
        assertEquals("User ID not found in local storage.", (result as Resource.Error).message)
    }

    @Test
    fun `getUser API call fails - returns error message`() = runTest {
        val userId = USER_ID

        whenever(localDataSource.getUserId()).thenReturn(userId)
        whenever(userService.getUser(userId)).doAnswer { throw IOException(NO_INTERNET_CONNECTION) }

        val result = repository.getUser().first()

        assertTrue(result is Resource.Error)
        assertEquals("Network error: $NO_INTERNET_CONNECTION", (result as Resource.Error).message)
    }

    @Test
    fun `saveOrUpdateEmail - verifies email is saved`() = runTest {
        val testEmail = USER_EMAIL
        repository.saveOrUpdateEmail(testEmail)
        verify(localDataSource).saveOrUpdateEmail(testEmail)
    }

    @Test
    fun `getEmail - returns saved email`() = runTest {
        val testEmail = USER_EMAIL
        whenever(localDataSource.getEmail()).thenReturn(testEmail)
        val result = repository.getEmail()
        assertEquals(testEmail, result)
    }

    @Test
    fun `getEmail - returns null if no email is found`() = runTest {
        whenever(localDataSource.getEmail()).thenReturn(null)
        val result = repository.getEmail()
        assertNull(result)
    }
}
