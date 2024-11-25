package com.oguzhanozgokce.finishmarmarab2b.core.data.network.interceptor

import com.oguzhanozgokce.finishmarmarab2b.core.data.network.token.TokenRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val tokenRepository: dagger.Lazy<TokenRepository>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        TODO("Not yet implemented")
    }
}

// TMDM Api
// Mutex
// Main Activty token kontrolü

