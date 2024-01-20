package com.vrushi.emiaggregator.core.datastore

import android.content.Context
import com.vrushi.emiaggregator.core.util.AppExtensions.Companion.appSettingsDataStore
import kotlinx.coroutines.flow.Flow

class DataStoreRepositoryImpl(
    private val context: Context
) : DataStoreRepository {
    override fun getAppSettingsFlow(): Flow<AppSettings> {
        return context.appSettingsDataStore.data
    }

    override suspend fun setInitialStart(initialStart: Boolean) {
        context.appSettingsDataStore.updateData {
            it.copy(
                initialStartUp = initialStart
            )
        }
    }

    override suspend fun setAppLanguage(language: AppLanguage) {
        context.appSettingsDataStore.updateData {
            it.copy(
                appLanguage = language
            )
        }
    }

    override suspend fun setAppBackupFrequency(frequency: AppBackupFrequency) {
        context.appSettingsDataStore.updateData {
            it.copy(
                appBackupFrequency = frequency
            )
        }
    }

    override suspend fun setAppTheme(theme: AppTheme) {
        context.appSettingsDataStore.updateData {
            it.copy(
                appTheme = theme
            )
        }
    }

    override suspend fun setBackupFolder(path: String) {
        context.appSettingsDataStore.updateData {
            it.copy(
                backupFolder = path
            )
        }
    }
}