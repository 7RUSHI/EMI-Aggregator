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
}