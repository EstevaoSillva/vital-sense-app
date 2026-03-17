package com.mindsense.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    private val nameKey = stringPreferencesKey("user_name")

    fun observeUserName(): Flow<String?> = dataStore.data.map { it[nameKey] }

    suspend fun setUserName(name: String) {
        dataStore.edit { it[nameKey] = name }
    }
}
