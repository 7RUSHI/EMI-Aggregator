package com.vrushi.emiaggregator.core.datastore

import androidx.datastore.core.DataStore

object AppSettingsDataStoreExtensions {
    suspend fun DataStore<AppSettings>.setInitialStart(initialStart: Boolean){
        updateData {
            it.copy(
                initialStartUp = initialStart
            )
        }
    }
    suspend fun DataStore<AppSettings>.setAppLanguage(language: AppLanguage){
        updateData {
            it.copy(
                appLanguage = language
            )
        }
    }
    suspend fun DataStore<AppSettings>.setAppBackupFrequency(frequency: AppBackupFrequency){
        updateData {
            it.copy(
                appBackupFrequency = frequency
            )
        }
    }
}