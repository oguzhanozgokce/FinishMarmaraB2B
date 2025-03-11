package com.oguzhanozgokce.finishmarmarab2b.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Location
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {

    val LocationType = object : NavType<Location>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): Location? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Location {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Location): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: Location) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}
