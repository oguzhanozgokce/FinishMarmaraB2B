package com.oguzhanozgokce.finishmarmarab2b.navigation

import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductListType
import kotlinx.serialization.Serializable

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
data object Address

@Serializable
data object Evaluation

@Serializable
data class Products(
    val id: Int? = null,
    val name: String? = null,
    val searchQuery: String? = null,
    val type: ProductListType,
)
