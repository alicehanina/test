package com.grzeluu.habittracker.source.preferences.di

import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.grzeluu.habittracker.source.preferences.data.datastore.SettingsDataSource
import com.grzeluu.habittracker.source.preferences.data.datastore.SettingsDataSourceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val USER_PREFERENCES = "user_settings_preferences"
val SETTINGS_STORE = named("SettingsStore")

val preferencesModule = module {
    single(SETTINGS_STORE) {
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(androidContext(), USER_PREFERENCES)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { androidContext().preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }

    single<SettingsDataSource> { SettingsDataSourceImpl(get(SETTINGS_STORE)) }
}