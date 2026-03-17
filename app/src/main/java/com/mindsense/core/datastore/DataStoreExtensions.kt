package com.mindsense.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreFileProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun create(name: String): File = context.preferencesDataStoreFile(name)
}
