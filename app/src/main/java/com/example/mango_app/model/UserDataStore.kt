package com.example.mango_app.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")

class UserDataStore(private val context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val AUTH_TOKEN = stringPreferencesKey("user_auth_token")
        val EMAIL = stringPreferencesKey("user_email")
        val NAME = stringPreferencesKey("user_name")
    }

    suspend fun saveAuthData(token: String, email: String, name: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_TOKEN] = token
            preferences[EMAIL] = email
            preferences[NAME] = name
        }
    }

    suspend fun saveAuthToken(token: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_TOKEN] = token
        }
    }

    suspend fun clearAuthData() {
        dataStore.edit { preferences ->
            preferences.remove(AUTH_TOKEN)
            preferences.remove(EMAIL)
            preferences.remove(NAME)
        }
    }

    fun getAuthToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[AUTH_TOKEN]
        }
    }

    fun getEmail(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[EMAIL]
        }
    }

    fun getName(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[NAME]
        }
    }

}