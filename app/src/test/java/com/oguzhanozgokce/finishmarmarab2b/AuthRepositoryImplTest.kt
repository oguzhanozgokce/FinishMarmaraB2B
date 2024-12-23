package com.oguzhanozgokce.finishmarmarab2b

import com.oguzhanozgokce.finishmarmarab2b.core.common.Resource
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.repository.AuthRepositoryImpl
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.ApiService
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.dto.UserDto
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignInRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.request.SignUpRequest
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.ApiResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.SignInResponse
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.source.remote.response.SignUpResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.whenever
import java.io.IOException

private const val USER_ID = 1
private const val USER_NAME = "Oguzhan"
private const val USER_SURNAME = "Ozgokce"
private const val USER_EMAIL = "ozgokceoguzhan34@fm.com"
private const val NOT_USER_EMAIL = "william.cushing@altostrat.com"
private const val USER_PASSWORD = "123456"
private const val USER_PHONE_NUMBER = "1234567890"
private const val USER_NULL = ""
private const val TEST_ZERO = 0
private const val SUCCESS = "Success"
private const val ERROR = "Error"
private const val NO_INTERNET_CONNECTION = "No internet connection."

class AuthRepositoryImplTest {
    private lateinit var repository: AuthRepositoryImpl
    private val apiService: ApiService =  mock()

    @Before
    fun setUp() {
        repository = AuthRepositoryImpl(apiService)
    }

    @Test
    fun `signIn success - returns User`() = runTest {
        val signInRequest = SignInRequest(
            id = USER_ID,
            email = USER_EMAIL,
            password = USER_PASSWORD
        )

        val userDto = UserDto(
            id = USER_ID,
            name = USER_NAME,
            surname = USER_SURNAME,
            email = USER_EMAIL,
            password = USER_PASSWORD,
            phoneNumber = USER_PHONE_NUMBER,
            birthDate = USER_NULL,
            locationId = TEST_ZERO,
            favoriteId = TEST_ZERO,
            notificationId = TEST_ZERO,
            creditCardId = TEST_ZERO,
            orderId = TEST_ZERO,
            registerDate = USER_NULL
        )

        val apiResponse = ApiResponse(
            status = true,
            message = SUCCESS,
            data = SignInResponse(userDto)
        )

        whenever(apiService.signIn(signInRequest)).thenReturn(apiResponse)

        val result = repository.signIn(signInRequest).first()

        assert(result is Resource.Success)
        val user = (result as Resource.Success).data
        assertEquals(USER_ID, user.id)
        assertEquals(USER_NAME, user.name)
        assertEquals(USER_SURNAME, user.surname)
        assertEquals(USER_EMAIL, user.email)
    }

    @Test
    fun `signIn error - returns error message`() = runTest {
        val signInRequest = SignInRequest(
            id = USER_ID,
            email = NOT_USER_EMAIL,
            password = USER_PASSWORD,
        )

        val apiResponse = ApiResponse<SignInResponse>(
            status = false,
            message = ERROR,
            data = null
        )

        whenever(apiService.signIn(signInRequest)).thenReturn(apiResponse)

        val result = repository.signIn(signInRequest).first()

        assert(result is Resource.Error)
        val errorMessage = (result as Resource.Error).message
        assertEquals(ERROR, errorMessage)
    }

    @Test
    fun `signIn exception - returns Resource Error with default message`() = runTest {
        val signInRequest = SignInRequest(
            id = USER_ID,
            email = USER_EMAIL,
            password = USER_PASSWORD,
        )
        whenever(apiService.signIn(signInRequest)).doAnswer {
            throw IOException(NO_INTERNET_CONNECTION)
        }
        val result = repository.signIn(signInRequest).first()
        assert(result is Resource.Error)
        val errorMessage = (result as Resource.Error).message
        assertEquals(NO_INTERNET_CONNECTION, errorMessage)
    }

    @Test
    fun `signUp success - returns User`() = runTest {
        val signUpRequest = SignUpRequest(
            id = USER_ID,
            name = USER_NAME,
            surname = USER_SURNAME,
            email = USER_EMAIL,
            password = USER_PASSWORD,
            phoneNumber = USER_PHONE_NUMBER
        )
        val userDto = UserDto(
            id = USER_ID,
            name = USER_NAME,
            surname = USER_SURNAME,
            email = USER_EMAIL,
            phoneNumber = USER_PHONE_NUMBER,
            birthDate = USER_NULL,
            locationId = TEST_ZERO,
            favoriteId = TEST_ZERO,
            notificationId = TEST_ZERO ,
            creditCardId = TEST_ZERO,
            orderId = TEST_ZERO,
            registerDate = USER_NULL
        )
        val apiResponse = ApiResponse(
            status = true,
            message = SUCCESS,
            data = SignUpResponse(userDto)
        )
        whenever(apiService.signUp(signUpRequest)).thenReturn(apiResponse)
        val result = repository.signUp(signUpRequest).first()
        assert(result is Resource.Success)
        val user = (result as Resource.Success).data
        assertEquals(USER_ID, user.id)
        assertEquals(USER_NAME, user.name)
        assertEquals(USER_SURNAME, user.surname)
        assertEquals(USER_EMAIL, user.email)
        assertEquals(USER_PHONE_NUMBER, user.phoneNumber)
    }

    @Test
    fun `signUp error - returns error message`() = runTest {
        val signUpRequest = SignUpRequest(
            id = USER_ID,
            name = USER_NAME,
            surname = USER_SURNAME,
            email = USER_EMAIL,
            password = USER_PASSWORD,
            phoneNumber = USER_PHONE_NUMBER
        )

        val apiResponse = ApiResponse<SignUpResponse>(
            status = false,
            message = ERROR,
            data = null
        )

        whenever(apiService.signUp(signUpRequest)).thenReturn(apiResponse)

        val result = repository.signUp(signUpRequest).first()

        assert(result is Resource.Error)
        val errorMessage = (result as Resource.Error).message
        assertEquals(ERROR, errorMessage)
    }
}