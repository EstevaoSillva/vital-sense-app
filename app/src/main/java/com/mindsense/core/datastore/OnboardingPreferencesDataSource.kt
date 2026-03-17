package com.mindsense.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnboardingPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    private val key = booleanPreferencesKey("has_completed_onboarding")

    fun observeCompleted(): Flow<Boolean> = dataStore.data.map { it[key] ?: false }

    suspend fun setCompleted(completed: Boolean) {
        dataStore.edit { it[key] = completed }
    }
}
