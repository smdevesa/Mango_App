package com.example.mango_app.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mango_app.model.data.UserInfoResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")

class UserDataStore(private val context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val AUTH_TOKEN = stringPreferencesKey("user_auth_token")
        val USER_ID = intPreferencesKey("user_id")
        val USER_FIRST_NAME = stringPreferencesKey("user_first_name")
        val USER_LAST_NAME = stringPreferencesKey("user_last_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_BIRTH_DATE = stringPreferencesKey("user_birth_date")
    }

    suspend fun saveAuthToken(token: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_TOKEN] = token
        }
    }

    fun getAuthToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[AUTH_TOKEN]
        }
    }

    suspend fun saveUserInfo(userInfoResponse: UserInfoResponse) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = userInfoResponse.id
            preferences[USER_FIRST_NAME] = userInfoResponse.firstName
            preferences[USER_LAST_NAME] = userInfoResponse.lastName
            preferences[USER_EMAIL] = userInfoResponse.email
            preferences[USER_BIRTH_DATE] = userInfoResponse.birthDate
        }
    }

    fun getUserInfo(): Flow<UserInfoResponse?> {
        return dataStore.data.map { preferences ->
            UserInfoResponse(
                id = preferences[USER_ID] ?: 0,
                firstName = preferences[USER_FIRST_NAME] ?: "",
                lastName = preferences[USER_LAST_NAME] ?: "",
                email = preferences[USER_EMAIL] ?: "",
                birthDate = preferences[USER_BIRTH_DATE] ?: ""
            )
        }
    }

}