package com.vrushi.emiaggregator.feature_settings.presentation.components

import com.vrushi.emiaggregator.core.datastore.AppBackupFrequency
import com.vrushi.emiaggregator.core.datastore.AppLanguage
import com.vrushi.emiaggregator.core.datastore.AppTheme

sealed class SettingItemEvents {
    data class OnAppLanguageChange(val language: AppLanguage): SettingItemEvents()
    data class OnAppThemeChange(val theme: AppTheme): SettingItemEvents()
    data class OnAppBackupScheduleChange(val frequency: AppBackupFrequency): SettingItemEvents()
    object OnOutputFolderChange: SettingItemEvents()
    object OnExportDB: SettingItemEvents()
    object OnExportAsCSV: SettingItemEvents()
    object OnImportDB: SettingItemEvents()
    object OnBugReport: SettingItemEvents()
    object OnAbout: SettingItemEvents()
}