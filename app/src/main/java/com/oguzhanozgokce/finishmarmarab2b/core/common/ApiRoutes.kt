package com.oguzhanozgokce.finishmarmarab2b.core.common

object ApiRoutes {
    const val LOGIN = "/api/Auth/user/login"
    const val REGISTER = "/api/Auth/user/register"
    const val GET_USER = "/api/User/{id}"
    const val GET_PRODUCTS = "/api/Product"
    const val POST_TOGGLE_FAVORITE = "/api/Favorite/toggle-fav"
    const val GET_FAVORITE_PRODUCTS = "/api/Favorite/{user_id}"
    const val DELETE_FAVORITE_PRODUCT = "/api/Favorite/{user_id}/{product_id}"
    const val POST_ADD_FAVORITE_PRODUCT = "/api/Favorite/{user_id}"
    const val GET_COMMENT_PRODUCT = "/api/Comment/{product_id}"
    const val GET_PRODUCT_DETAIL = "/api/Product/{id}"
    const val GET_QUESTIONS_PRODUCT = "/api/Question/{product_id}"
    const val GET_CATEGORIES = "/api/Category"
    const val GET_PRODUCT_TOP5 = "/api/Product/top5"
}
