package com.oguzhanozgokce.finishmarmarab2b.core.domain.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityListener {
    val isNetworkAvailable: Flow<Boolean>
}