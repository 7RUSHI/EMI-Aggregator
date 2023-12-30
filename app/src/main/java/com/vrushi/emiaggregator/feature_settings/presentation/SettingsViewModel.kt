package com.vrushi.emiaggregator.feature_settings.presentation

import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrushi.emiaggregator.R
import com.vrushi.emiaggregator.core.datastore.DataStoreRepository
import com.vrushi.emiaggregator.core.util.AppExtensions
import com.vrushi.emiaggregator.feature_settings.presentation.components.SettingItemEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    private val settingsProvider = listOf<SettingProvider>(
        AppLanguageSettingProvider(),
        AppThemeSettingProvider(),
        AppScheduleBackupSettingProvider(),
        OutputFolderSettingProvider(),
        ExportCSVSettingProvider(),
        ExportDBSettingProvider(),
        ImportDBSettingProvider(),
        BugReportSettingProvider(),
        AboutSettingProvider()
    )
    val grouped = settingsProvider.groupBy { it.category }
    private val _sharedFlow = MutableSharedFlow<SettingsScreenEvents>()
    val sharedFlow = _sharedFlow.asSharedFlow()
    fun onEvent(event: SettingItemEvents) {
        when (event) {
            is SettingItemEvents.OnAbout -> {
                viewModelScope.launch {
                    _sharedFlow.emit(SettingsScreenEvents.ShowSnackbar(message = R.string.app_settings_about_headline))
                }
            }

            is SettingItemEvents.OnAppBackupScheduleChange -> {
                viewModelScope.launch {
                    _sharedFlow.emit(SettingsScreenEvents.ShowSnackbar(message = R.string.app_settings_backup_category))
                }
            }

            is SettingItemEvents.OnAppLanguageChange -> {
                val appLocal: LocaleListCompat = AppExtensions.getLanguageTag(event.language)
                viewModelScope.launch {
                    dataStoreRepository.setAppLanguage(event.language)
                    _sharedFlow.emit(SettingsScreenEvents.ChangeAppLanguage(appLocal))
                }
            }

            is SettingItemEvents.OnExportAsCSV -> {

            }

            is SettingItemEvents.OnExportDB -> {

            }

            is SettingItemEvents.OnImportDB -> {

            }

            is SettingItemEvents.OnOutputFolderChange -> {

            }

            is SettingItemEvents.OnBugReport -> {

            }
        }
    }
}


interface SettingProvider {
    val category: Int
    val headline: Int
    val support: Int?
    val options: List<Int>
}

class AppLanguageSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_intetface_category
    override val headline: Int = R.string.app_settings_language_headline
    override val support: Int = R.string.app_settings_language_support
    override val options: List<Int> = listOf(
        R.string.app_settings_language_option_english,
        R.string.app_settings_language_option_marathi
    )
}

class AppThemeSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_intetface_category
    override val headline: Int = R.string.app_settings_theme_headline
    override val support: Int = R.string.app_settings_theme_support
    override val options: List<Int> = listOf(
        R.string.app_settings_theme_option_light,
        R.string.app_settings_theme_option_light
    )
}

class AppScheduleBackupSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_backup_category
    override val headline: Int = R.string.app_settings_schedule_headline
    override val support: Int = R.string.app_settings_schedule_support
    override val options: List<Int> = listOf(
        R.string.app_settings_schedule_option_daily,
        R.string.app_settings_schedule_option_weekly,
        R.string.app_settings_schedule_option_monthly
    )
}

class OutputFolderSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_backup_category
    override val headline: Int = R.string.app_settings_outputfolder_headline
    override val support: Int = R.string.app_settings_outputfolder_support
    override val options: List<Int> = emptyList()
}

class ExportDBSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_database_category
    override val headline: Int = R.string.app_settings_exportdb_headline
    override val support: Int = R.string.app_settings_exportdb_support
    override val options: List<Int> = emptyList()
}

class ExportCSVSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_database_category
    override val headline: Int = R.string.app_settings_exportcsv_headline
    override val support: Int = R.string.app_settings_exportcsv_support
    override val options: List<Int> = emptyList()
}

class ImportDBSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_database_category
    override val headline: Int = R.string.app_settings_importdata_headline
    override val support: Int = R.string.app_settings_importdata_support
    override val options: List<Int> = emptyList()
}

class BugReportSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_troubleshooting_category
    override val headline: Int = R.string.app_settings_bugreport_headline
    override val support: Int? = null
    override val options: List<Int> = emptyList()
}

class AboutSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_links_category
    override val headline: Int = R.string.app_settings_about_headline
    override val support: Int? = null
    override val options: List<Int> = emptyList()
}

