package com.oguzhanozgokce.finishmarmarab2b.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Location
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductListType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data object Home

@Serializable
data object Profile

@Serializable
data object Favorite

@Serializable
data object Cart

@Serializable
data object Payment

@Serializable
data class Detail(val id: Int)

@Serializable
data object Welcome

@Serializable
data object Login

@Serializable
data object Signup

@Serializable
data object ForgotPassword

@Serializable
data object Search

@Serializable
data object Splash

@Serializable
data class Address(val location: Location) {
    companion object {
        val typeMap = mapOf(typeOf<Location>() to serializableType<Location>())
        fun from(savedStateHandle: SavedStateHandle) = savedStateHandle.toRoute<Address>(typeMap)
        fun default() = Address(Location.default())
    }
}

@Serializable
data object Evaluation

@Serializable
data object OrderSuccess

@Serializable
data class Products(
    val id: Int? = null,
    val name: String? = null,
    val searchQuery: String? = null,
    val type: ProductListType,
)
