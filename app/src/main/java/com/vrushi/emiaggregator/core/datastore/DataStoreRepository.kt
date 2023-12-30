package com.vrushi.emiaggregator.core.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    fun getAppSettingsFlow(): Flow<AppSettings>
    suspend fun setInitialStart(initialStart: Boolean)
    suspend fun setAppLanguage(language: AppLanguage)
    suspend fun setAppBackupFrequency(frequency: AppBackupFrequency)
}