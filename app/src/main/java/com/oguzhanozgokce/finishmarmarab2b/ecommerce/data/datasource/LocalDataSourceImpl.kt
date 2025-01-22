package com.oguzhanozgokce.finishmarmarab2b.ecommerce.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.datasource.LocalDataSource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalDataSource {
    companion object {
        private val USER_ID_KEY = stringPreferencesKey("id")
        private val EMAIL_KEY = stringPreferencesKey("email")
    }

    override suspend fun saveOrUpdateUserId(id: Int) {
        dataStore.edit { preferences ->
            val currentId = preferences[USER_ID_KEY]?.toIntOrNull()
            if (currentId != id) {
                preferences[USER_ID_KEY] = id.toString()
            }
        }
    }

    override suspend fun getUserId(): Int? {
        val preferences = dataStore.data.first()
        return preferences[USER_ID_KEY]?.toIntOrNull()
    }

    override suspend fun clearUserId() {
        dataStore.edit { preferences ->
            preferences.remove(USER_ID_KEY)
        }
    }

    override suspend fun saveOrUpdateEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
        }
    }

    override suspend fun getEmail(): String? {
        val preferences = dataStore.data.first()
        return preferences[EMAIL_KEY]
    }

    override suspend fun clearEmail() {
        dataStore.edit { preferences ->
            preferences.remove(EMAIL_KEY)
        }
    }
}
