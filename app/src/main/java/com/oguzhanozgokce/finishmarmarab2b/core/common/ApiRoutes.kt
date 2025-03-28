package com.oguzhanozgokce.finishmarmarab2b.core.common

object ApiRoutes {
    const val LOGIN = "/api/Auth/user/login"
    const val REGISTER = "/api/Auth/user/register"
    const val GET_USER = "/api/User/{id}"
    const val PUT_USER = "/api/User"
    const val GET_PRODUCTS = "/api/Product"
    const val POST_TOGGLE_FAVORITE = "/api/Favorite/toggle-fav"
    const val GET_FAVORITE_PRODUCTS = "/api/Favorite/{user_id}"
    const val DELETE_FAVORITE_PRODUCT = "/api/Favorite/{user_id}/{product_id}"
    const val GET_COMMENT_PRODUCT = "/api/Comment/{product_id}"
    const val GET_PRODUCT_DETAIL = "/api/Product/{id}"
    const val GET_QUESTIONS_PRODUCT = "/api/Question/{product_id}"
    const val GET_CATEGORIES = "/api/Category"
    const val GET_PRODUCT_TOP5 = "/api/Product/top5"
    const val POST_BASKET = "/api/Basket"
    const val GET_BASKET = "/api/Basket/{user_id}"
    const val DELETE_BASKET = "/api/Basket/{user_id}/{product_id}"
    const val DELETE_BASKET_ALL = "/api/Basket/delete-all/{user_id}"
    const val POST_SAVE_ADDRESS = "/api/Location"
    const val GET_LOCATIONS = "/api/Location/{user_id}"
    const val PUT_LOCATION = "/api/Location"
    const val DELETE_LOCATION = "/api/Location/{location_id}"
    const val GET_USER_SEARCH_HISTORY = "/api/UserSearchHistory"
    const val DELETE_USER_SEARCH_HISTORY = "/api/UserSearchHistory/{id}"
    const val DELETE_USER_ALL_SEARCH_HISTORY = "/api/UserSearchHistory/user/{user_id}"
    const val POST_ORDER = "/api/Order"
    const val POST_CREDIT_CARD = "/api/CreditCard"
    const val GET_CREDIT_CARD = "/api/CreditCard/{user_id}"
    const val GET_COLLECTIONS = "/api/Collection/{user_id}"
    const val POST_COLLECTION = "/api/Collection"
    const val POST_COLLECTION_ADD_PRODUCT = "/api/Collection/add-product"
    const val POST_COLLECTION_ADD_PRODUCTS = "/api/Collection/add-products"
    const val DELETE_COLLECTION = "/api/Collection/{collection_id}"
    const val PUT_COLLECTION = "/api/Collection/{collection_id}"
    const val GET_ORDER_INFO = "/api/Order/{order_id}"
}
