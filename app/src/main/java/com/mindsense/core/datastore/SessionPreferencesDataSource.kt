package com.mindsense.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    private val loggedInKey = booleanPreferencesKey("is_logged_in")
    private val tokenKey = stringPreferencesKey("auth_token")

    fun observeLoggedIn(): Flow<Boolean> = dataStore.data.map { it[loggedInKey] ?: false }

    suspend fun setLoggedIn(loggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[loggedInKey] = loggedIn
            preferences[tokenKey] = if (loggedIn) "fake-token" else ""
        }
    }
}
