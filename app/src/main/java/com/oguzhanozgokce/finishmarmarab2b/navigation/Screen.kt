package com.oguzhanozgokce.finishmarmarab2b.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Home : Screen

    @Serializable
    data object Profile : Screen

    @Serializable
    data object Favorite : Screen

    @Serializable
    data object Cart : Screen

    @Serializable
    data object Payment : Screen

    @Serializable
    data class Detail(val id: Int) : Screen

    @Serializable
    data object Welcome : Screen

    @Serializable
    data object Login : Screen

    @Serializable
    data object Signup : Screen

    @Serializable
    data object ForgotPassword : Screen
}

fun Screen.getRoute(): String = this::class.qualifiedName.orEmpty()
