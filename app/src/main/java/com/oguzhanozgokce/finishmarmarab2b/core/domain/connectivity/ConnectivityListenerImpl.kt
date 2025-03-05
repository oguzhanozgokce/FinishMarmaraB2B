package com.oguzhanozgokce.finishmarmarab2b.core.domain.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ConnectivityListenerImpl(context: Context) : ConnectivityListener {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    override val isNetworkAvailable: Flow<Boolean> = callbackFlow {
        if (connectivityManager == null) {
            trySend(false)
            close()
            return@callbackFlow
        }
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val isConnected =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                trySend(isConnected)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                trySend(element = false)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(element = false)
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(element = true)
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback)
        val activeNetwork = connectivityManager.activeNetwork
        val isConnected = activeNetwork?.let {
            val capabilities = connectivityManager.getNetworkCapabilities(it)
            capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
        } ?: false

        trySend(isConnected)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
}
