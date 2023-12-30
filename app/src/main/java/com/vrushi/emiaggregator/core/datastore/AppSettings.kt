package com.vrushi.emiaggregator.core.datastore

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val initialStartUp: Boolean = true,
    val appLanguage: AppLanguage = AppLanguage.ENGLISH,
    val appBackupFrequency: AppBackupFrequency = AppBackupFrequency.DAILY,
    val backupFolder: String = "",
    val appTheme: AppTheme = AppTheme.SYSTEM
)

enum class AppLanguage{
    ENGLISH, MARATHI
}

enum class AppBackupFrequency{
    DAILY, WEEKLY, MONTHLY
}

enum class AppTheme{
    LIGHT, DARK, SYSTEM
}